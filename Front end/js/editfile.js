// Strict mode
'use strict';

 // * Get id from link
 let link = window.location.search;
 let urlPmtr = new URLSearchParams(link);
 let id2 = urlPmtr.get('id');
 let id = parseInt(id2);
 let int = Number.isInteger(id);

 if(int === true){
     fetch(`http://localhost:8091/livefiles/read/id/${id}`)
     .then((response) => {
         if (response.status !== 200) {
            location.replace("error.html");
         }
         response.json()
         .then((data) => {
             let titleField = document.getElementById("updateTitle");
             let labelField = document.getElementById("updateLabel");
             let contentField = document.getElementById("updateContent");
        
             titleField.value = data.title;
             labelField.value = data.label;
             contentField.value = data.content;
         }) 
         .catch((error) => console.log(`Error ${error}`));
     })


 } 

// form
editFile.onsubmit = async (e) => {
    e.preventDefault();
    let formTitle = document.getElementById("updateTitle").value;
    let formLabel = document.getElementById("updateLabel").value;
    let formContent = document.getElementById("updateContent").value;

    // * Testing
    /* console.log(formTitle);
    console.log(formLabel);
    console.log(formContent); */

    // * Get id from link
    let link = window.location.search;
    let urlPmtr = new URLSearchParams(link);
    let id2 = urlPmtr.get('id');
    let id = parseInt(id2);
    let int = Number.isInteger(id);

    const File = {
        "record_id": id,
        "title": formTitle,
        "label": formLabel,
        "content": formContent
    };
    
    // * Testing
     /* console.log(File);  */

    if(int === true){     
        //fetch POST method
        fetch(`http://localhost:8091/livefiles/update/${id}`,{
            method: `PUT`, // Declaring the method
            headers: { 
                "Content-type":"application/json" // Header
            },
            body: JSON.stringify(File) // What data to post
        })
        .then( (response) => {
            if (response.status !== 202) { // Status code of 201
                console.log(`Status ${response.status}`);
                return;
            } else {
                location.replace("success.html");
            }
            response.json()
            .then( (data) => console.log("Success"))
            .catch( (error) => console.log(error))
        });
    }
}
