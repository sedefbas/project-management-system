package management.sedef.issue.validation;

import management.sedef.issue.exception.MaxIssueLimitReachedException;
import management.sedef.subscriptionPlan.model.SubscriptionPlan;

public class IssueValidator {

    /**
     * Proje ID'sine göre issue sayısının, ilgili subscription planındaki max_task limitini aşıp aşmadığını kontrol eder.
     * @param subscriptionPlan: Şirketin abone olduğu plan.
     * @param existingIssueCount: İlgili projedeki mevcut issue sayısı.
     * @throws MaxIssueLimitReachedException: Eğer max_task limitine ulaşılmışsa özel exception fırlatılır.
     */


    public static void validateMaxIssues(SubscriptionPlan subscriptionPlan, long existingIssueCount) {
        if (existingIssueCount >= subscriptionPlan.getMaxTasks()) {
            throw new MaxIssueLimitReachedException("Bu proje için maksimum issue sayısına ulaşıldı. Yeni issue açılamaz.");
        }
    }
}
