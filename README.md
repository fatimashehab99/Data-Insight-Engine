CREATE TABLE data_insight_engine.page_views (
    post_id STRING,
    ip STRING,
    browser STRING,
    device STRING,
    post_type STRING,
    post_image STRING,
    domain STRING,
    user_id STRING,
    post_url STRING,
    post_category STRING,
    post_publish_date STRING,
    date STRING,
    country_name STRING,
    country_code STRING
);

CREATE TABLE data_insight_engine.post_tags (
    post_id STRING,
    tags STRING
);
