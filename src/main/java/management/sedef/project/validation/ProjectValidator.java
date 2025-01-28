package management.sedef.project.validation;

import management.sedef.project.exception.MaxProjectLimitReachedException;
import management.sedef.project.exception.MaxUserLimitReachedException;
import management.sedef.subscriptionPlan.model.SubscriptionPlan;


public class ProjectValidator {

    /**
     * Proje sayısının max proje sayısını aşıp aşmadığını kontrol eder.
     * @param subscriptionPlan: Şirketin abone olduğu plan.
     * @param existingProjectCount: Şirketin mevcut proje sayısı.
     * @throws MaxProjectLimitReachedException: Eğer maksimum proje sayısına ulaşılmışsa özel exception fırlatılır.
     */
    public static void validateMaxProjects(SubscriptionPlan subscriptionPlan, int existingProjectCount) {
        if (existingProjectCount >= subscriptionPlan.getMaxProjects()) {
            throw new MaxProjectLimitReachedException("Bu şirketin açabileceği maksimum proje sayısına ulaşıldı.");
        }
    }

    /**
     * Kullanıcı sayısının max kullanıcı sayısını aşıp aşmadığını kontrol eder.
     * @param subscriptionPlan: Şirketin abone olduğu plan.
     * @param existingUserCount: Şirketin mevcut kullanıcı sayısı.
     * @throws MaxUserLimitReachedException: Eğer maksimum kullanıcı sayısına ulaşılmışsa özel exception fırlatılır.
     */
    public static void validateMaxUsers(SubscriptionPlan subscriptionPlan, int existingUserCount) {
        if (existingUserCount >= subscriptionPlan.getMaxUsers()) {
            throw new MaxUserLimitReachedException("Bu şirketin ekleyebileceği maksimum kullanıcı sayısına ulaşıldı.");
        }
    }
}