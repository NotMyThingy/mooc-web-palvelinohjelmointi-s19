var url = contextRoot + "tasks";
var http = new XMLHttpRequest();


function loadTasks() {
    http.onreadystatechange = function () {
        if (this.readyState !== 4 || this.status !== 200) {
            return;
        }

        var tasklist = JSON.parse(this.responseText);
        for (var i in tasklist) {
            console.log("listalta ladattua: " + i);
            addTaskToList(tasklist[i]);
        }
    };

    http.open("GET", url, true);
    http.send();
}

function addTask() {
    var task = {
        name: document.querySelector("#task").value
    };

    http.open("POST", url, true);
    http.setRequestHeader('Content-Type', 'application/json');
    http.onreadystatechange = function () {
        if (this.readyState !== 4 || this.status !== 200) {
            return;
        }

        addTaskToList(JSON.parse(http.responseText));
    };

    http.send(JSON.stringify(task));

    document.getElementById("myForm").reset();
}

function addTaskToList(task) {
    var li = document.createElement("li");
    li.appendChild(document.createTextNode(task.name));
    document.querySelector("#tasks").appendChild(li);
}
