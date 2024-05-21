# BigQuery Queries

The following code show the page views schemas
```
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
    post_publish_date DateTime,
    date DateTime,
    country_name STRING,
    country_code STRING,
    post_tags Array<String>,
    post_author STRING
);

```
