package management.sedef.issue.validation;

import management.sedef.issue.Service.IssueLinkService;
import management.sedef.issue.exception.IssueBlockedException;
import management.sedef.issue.exception.IssueDependencyException;
import management.sedef.issue.exception.MaxIssueLimitReachedException;
import management.sedef.issue.model.Issue;
import management.sedef.issue.model.enums.IssueLinkType;
import management.sedef.stage.model.enums.StageType;
import management.sedef.subscriptionPlan.model.SubscriptionPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class IssueValidator {

    @Autowired
    private  IssueLinkService issueLinkService;
    /**
     * Proje ID'sine göre issue sayısının, ilgili subscription planındaki max_task limitini aşıp aşmadığını kontrol eder.
     * @param subscriptionPlan: Şirketin abone olduğu plan.
     * @param existingIssueCount: İlgili projedeki mevcut issue sayısı.
     * @throws MaxIssueLimitReachedException: Eğer max_task limitine ulaşılmışsa özel exception fırlatılır.
     */


    public void validateMaxIssues(SubscriptionPlan subscriptionPlan, long existingIssueCount) {
        if (existingIssueCount >= subscriptionPlan.getMaxTasks()) {
            throw new MaxIssueLimitReachedException("Bu proje için maksimum issue sayısına ulaşıldı. Yeni issue açılamaz.");
        }
    }


 //todo eger done olmussa depencedyden silinmesi gerekir. yada done olmayanları getir dicez.
    public void validateIssueStageChange(Long issueId, StageType newStage) {

        Map<IssueLinkType, Set<Issue>> linkedIssues = issueLinkService.getLinkedIssues(issueId);

        if (linkedIssues.containsKey(IssueLinkType.BLOCKS)) {
            throw new IssueBlockedException("Bu issue bloklandığı için başlanamaz.");
        }

        if (newStage == StageType.DONE && linkedIssues.containsKey(IssueLinkType.DEPENDS_ON)) {
            Set<Issue> dependencies = linkedIssues.get(IssueLinkType.DEPENDS_ON);
            boolean allDependenciesCompleted = dependencies.stream()
                    .allMatch(issue -> issue.getStage().getName() == StageType.DONE);

            if (!allDependenciesCompleted) {
                throw new IssueDependencyException("Bağlı olduğu issue'lar tamamlanmadan DONE durumuna geçemez.");
            }
        }
    }

}
