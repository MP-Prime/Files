// Strict mode
'use strict';

// form
createFile.onsubmit = async (e) => {
    e.preventDefault();
    let formTitle = document.getElementById("title").value;
    let formLabel = document.getElementById("label").value;
    let formContent = document.getElementById("content").value;

    // * Testing
    /* console.log(formTitle);
    console.log(formLabel);
    console.log(formContent); */

    const File = {
        "title": formTitle,
        "label": formLabel,
        "content": formContent
    };
    
    // * Testing
    /* console.log(File); */

    //fetch POST method
    fetch(`http://localhost:8091/livefiles/create`,{
        method: `POST`, // Declaring the method
        headers: { 
            "Content-type":"application/json" // Header
        },
        body: JSON.stringify(File) // What data to post
    })
    .then( (response) => {
        if (response.status !== 201) { // Status code of 201
            console.log(`Status ${response.status}`);
            return;
        } else {
            location.replace("success.html");
        }
        response.json()
        .then( (data) => console.log(`Request succesful with JSON response ${data}`))
        .catch( (error) => console.log(error))
    });
}




