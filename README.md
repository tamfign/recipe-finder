# Recipe Finder

Given a list of items in the fridge (presented as a csv list), and a collection of recipes (a collection of JSON formatted recipes), produce.
a recommendation for what to cook tonight.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purpose.

### Build

mvn package

### Running the tests

mvn test

### Running the application

java -jar target/gs-serving-web-content-0.1.0.jar

## Sample

### Items in Fridge (CSV format)

```json
bread,10,slices,25/12/2019
cheese,10,slices,25/12/2019
butter,250,grams,25/12/2019
peanut butter,250,grams,2/12/2019
mixed salad,150,grams,26/12/2018
```

### Recipes (JSON foramt)
```json
[
  {
    "name": "grilled cheese on toast",
    "ingredients": [
      {"item":"bread", "amount":"2", "unit":"slices"},
      {"item":"cheese", "amount":"2", "unit":"slices"}
    ]
  },
  {
    "name": "salad sandwich",
    "ingredients": [
      {"item":"bread", "amount":"2", "unit":"slices"},
      { "item":"mixed salad", "amount":"100", "unit":"grams"}
    ]
  }
]
```

## Authors

Andrew Yang

## Lincense

This project is licensed under the MIT License - see the LICENSE file for details
