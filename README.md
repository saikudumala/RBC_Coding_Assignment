
# RBC Coding Challenge

This Repository provides an application server that would allow multipe users to perform the
following operations concurrently:
- upload a bulk data set
- query for data by stock ticker (e.g. input: AA, would return 12 elements if the only data uploaded were the single data set above)
- add a new record





## API Reference

#### Upload stocks data

```http
  POST /stocks/upload
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `file` | `multiform/data` | Upload CSV file to store the stock data |

#### Get stocks related to stock sticker

```http
  GET /stocks/label/{stockSticker}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `stockSticker`      | `string` | Retrive the data related to stock label/sticker |

#### Add new stocks
```http
  POST /stocks/add
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `stock`      | `json` | Add a new stock to DB |




## Running Tests

Clone the project

```bash
  git clone https://link-to-project
```

Go to the project directory 

```bash
  Run the Main class DemoApplication.Java to start the server
```

The tests can be done through Open API or POSTMAN

Testing with the OPEN API:

```bash
  Go to : http://localhost:8080/swagger-ui/index.html#/
```

```bash
 For testing POST methods refer to src/main/resources folder.
 There dow_jones.csv can be used to test POST /stocks/upload and
 stock.json method can be used to test POST /stocks/add
```

Testing with the POSTMAN:

```bash
 Trigger the endpoints present in the api reference to validate the restful service.
 Example: POST :/stocks/upload 
 Please follow below steps to test this API:
 - Open Postman and give end point as http://localhost:8080/stocks/upload
 - Select Body and form-data
 - Select key as file and value as the csv file from src/main/resources/daw_jones_index.csv
 - Hit the end point and verify with http://localhost:8080/h2-console if the data is uploaded
```






