# Data Insight Engine

### Outline
### I. Introduction
Data Insight Engine is a comprehensive data anlytics tool with three main parts. First, it gathers important data from metadata websites using carefully made scripts and stores as JSON file. Then, it uses data flow to create strong pipelines for processing the data smoothly and storing it into BigQuery to get the data needed for visualization. Finally, it creates eye-catching charts and graphs using Looker Studio, making the data easy to understand. Overall, this system helps users collect, analyze, and display data in a straightforward and interactive manner, aiding them in making informed decisions.

### II. Data Transformation (Data Pipeline)
A Java Apache Beam is written to be run on a Dataflow pipeline. This code reads and parses the JSON lines stored in Cloud Storage containing the data. It then transforms it into page view objects, which are subsequently inserted into BigQuery for later analysis.

![Data flow pipeline template showing the stages during data transformation.](https://github.com/fatimashehab99/Data-Insight-Engine/blob/master/assets/Dataflow%20Pipeline.png)

**Data flow pipeline template showing the stages during data transformation.**


### III. Data Analysis Using Data WareHouse(Big Query) 
The following code show the page views and post tags big query schemas
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
    post_publish_date DATETIME,
    date DATESTRING,
    country_name STRING,
    country_code STRING
);
```
### IV.Data Visualization
![Data insight engine dashboard showing the charts design during data analysis.](https://github.com/fatimashehab99/Data-Insight-Engine/blob/master/assets/Data%20Engine%20Insight%20Dashboard.png)

**Data insight engine dashboard showing the charts design during data analysis.**
