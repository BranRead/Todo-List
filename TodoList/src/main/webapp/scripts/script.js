// function editTask(event){
//     event.preventDefault();
//     console.log("Worked");
// }


function test(){
    console.log("Test test");
}
function toggleDone(id, checkbox) {
    console.log(checkbox.checked);
    updateList(id, checkbox.checked).then(()=> {
        window.location.href = "http://localhost:8080/TodoList-1.0-SNAPSHOT/toDo.jsp";
    });
}

async function updateList(data, isChecked){
    await fetch("http://localhost:8080/TodoList-1.0-SNAPSHOT/login?taskId=" + data + "&isChecked=" + isChecked, {
        method: "GET"
    })
}

