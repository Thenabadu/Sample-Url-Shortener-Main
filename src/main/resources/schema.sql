DROP TABLE IF EXISTS sus_url;

CREATE TABLE sus_url (
    url_id INT NOT NULL AUTO_INCREMENT,
    url_key VARCHAR(10) NOT NULL UNIQUE,
    url TEXT NOT NULL,
    url_update_status INT NOT NULL,
    url_delete_status INT NOT NULL,
    url_expire_status INT NOT NULL,
    url_expire_date_time TIMESTAMP,
    url_delete_date_time TIMESTAMP,
    raw_created_date_time TIMESTAMP NOT NULL,
    raw_last_update_date_time TIMESTAMP NOT NULL,
    raw_active_status INT NOT NULL,
    url_hit INT NULL,
    PRIMARY KEY  (url_id)
);

CREATE INDEX ix_url_a_url_key ON sus_url (url_key);