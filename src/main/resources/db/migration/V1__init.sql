CREATE TABLE IF NOT EXISTS member
(
    member_id   BIGINT          NOT NULL AUTO_INCREMENT,
    social_id   VARCHAR(255)    NOT NULL,
    name        VARCHAR(255),
    email       VARCHAR(255),
    image_uri   VARCHAR(255),
    role        ENUM ('MEMBER','ADMIN'),
    social_type ENUM ('GOOGLE') NOT NULL,
    created_at  DATETIME(6),
    updated_at  DATETIME(6),
    PRIMARY KEY (member_id)
) engine = InnoDB;
