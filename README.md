# web-quiz-engine

# Description
RESTful service that allows users to perform basic CRUD operations on quiz questions, as well as answering them and getting a result.

## Current state
At this moment, user can post a quiz, get all quizzes, get a specific quiz and answer a quiz.

## Endpoints

* `POST /api/quizzes` - for posting a new quiz as JSON. The json will be validated - title and text cannot be empty, option cannot be null and has to containt at least 2 elements, 
answers is optional. 

**Example of a valid quiz:**

```
{
  "title": "Coffee drinks",
  "text": "Select only coffee drinks.",
  "options": ["Americano","Tea","Cappuccino","Sprite"],
  "answer": [0,2]
}
```

The endpoint also returns this quiz object as response:
```
{
    "id": 1,
    "title": "Coffee drinks",
    "text": "Select only coffee drinks.",
    "options": [
        "Americano",
        "Tea",
        "Cappuccino",
        "Sprite"
    ]
}
```
---

* `GET /api/quizzes/id` - for viewing a quiz with an id given in the URL. Returns a JSON representation of a quiz question.

**Example of a quiz JSON:**
```
{
    "id": 1,
    "title": "Coffee drinks",
    "text": "Select only coffee drinks.",
    "options": [
        "Americano",
        "Tea",
        "Cappuccino",
        "Sprite"
    ]
}
```
---
* `GET /api/quizzes` - for viewing all added quizzes. Returns a JSON with Quiz objects.
**Example:**
```
[
    {
        "id": 1,
        "title": "Coffee drinks",
        "text": "Select only coffee drinks.",
        "options": [
            "Americano",
            "Tea",
            "Cappuccino",
            "Sprite"
        ]
    },
    {
        "id": 2,
        "title": "Math",
        "text": "What is 2+2?",
        "options": [
            "1",
            "2",
            "4"
        ]
    }
]
```
---
* `POST /api/quizzes/id/solve` - for answering a quiz with an id given in the URL. Returns a JSON with feedback about the answer.
**Example answer:**
```
{
    "answer": [0, 2]
}
```

If the answer is correct, here is the response:
```
{
    "success": true,
    "feedback": "Congratulations, you're right!"
}
```

If the answer is not correct, here is the response:
```
{
    "success": false,
    "feedback": "Wrong answer! Please, try again."
}
```
---



