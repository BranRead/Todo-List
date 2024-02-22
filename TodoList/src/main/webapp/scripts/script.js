function toggleDone(id, checkbox) {
    // fetch("list", {
    //     method: "GET"
    // }).then(()=> {
    //
    // })
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