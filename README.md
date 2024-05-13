# Data Insight Engine

### Outline
### I. Introduction
Data Insight Engine is a comprehensive data anlytics tool with three main parts. First, it gathers important data from metadata websites using carefully made scripts and stores as JSON file. Then, it uses data flow to create strong pipelines for processing the data smoothly. After that, it lets users easily get the data they need for visualization through simple APIs. Finally, it creates eye-catching charts and graphs using Looker Studio, making the data easy to understand. Overall, this system helps users collect, analyze, and display data in a straightforward and interactive manner, aiding them in making informed decisions.

### II. Data Flow
A Java Apache Beam is written to be run on a Dataflow pipeline. This code reads and parses the JSON lines containing the data. It then transforms it into page view objects, which are subsequently inserted into BigQuery for later analysis.
<img src="![image](https://github.com/fatimashehab99/data-insight-engine/Screenshot 2024-05-13 130912.png" style=" width:600px ; height:500px "  >
Data flow pipeline template showing the stages during data transformation .

### III. Big Query 
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
    post_publish_date STRING,
    date STRING,
    country_name STRING,
    country_code STRING
);

CREATE TABLE data_insight_engine.post_tags (
    post_id STRING,
    tags STRING
);
```
