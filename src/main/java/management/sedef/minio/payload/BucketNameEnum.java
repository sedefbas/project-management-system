package management.sedef.minio.payload;

public enum BucketNameEnum {
    ANNOUNCEMENT_PHOTO("announcement-photo"),
    COMPANY_PHOTO("company-photo"),
    HELP_PHOTO("help-photo"),
    USERS_PHOTO("users-photo");

    private final String bucketName;

    BucketNameEnum(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }

    public static boolean isValidBucket(String bucketName) {
        for (BucketNameEnum bucket : values()) {
            if (bucket.getBucketName().equalsIgnoreCase(bucketName)) {
                return true;
            }
        }
        return false;
    }
}
