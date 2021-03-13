# To Do App with time zones
### Why did you make this?
Since I've never worked with time zones in Java, I decided to make a simple application that stores all timestamps in a database, then converts them to a specific time zone. This is a simple REST API "To Do App" that stores and displays todo items.

### REST API
### Create ToDo Item
**POST** `http://localhost:8086/todo-list?timezone=America/Chicago`
#### Example Request
    {
        "name": "Understand timezones in Java",
        "isCompleted": false,
        "startedAt": "03/13/2021 01:20 PM"
    }
#### Example Response
    {
        "id": 14,
        "name": "Understand timezones in Java",
        "startedAt": "2021-03-13T13:20:00-06:00",
        "endedAt": null,
        "completed": false
    }
***
### List All ToDo Items
**GET** `http://localhost:8086/todo-list?timezone=America/Chicago`
#### Example Response 
    [
        {
            "id": 1,
            "name": "Understand timezones in Java",
            "startedAt": "03/13/2021 01:20 PM",
            "endedAt": null,
            "completed": false
        }
    ]
