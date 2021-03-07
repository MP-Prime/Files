// Strict mode
'use strict';

// * Fetch all
fetch(`http://localhost:8091/livefiles/read`)
.then((response) => {
    if (response.status !== 200) {
        location.replace("error.html");
    }
    response.json()
    .then((data) => {
        postData(data);
    }) 
    .catch((error) => console.log(`Error ${error}`));
})

// * Display fetch all
function postData(data){
    for (let newVar = 0; newVar < data.length; newVar++){
        
        // * Testing
        // console.log(data[newVar].title);
        // console.log(data[newVar].content);
        // console.log(data[newVar].label);
        // console.log(data[newVar].record_id);

        // * Getting data
        let title = data[newVar].title;
        let label = data[newVar].label;
        let recordId = data[newVar].record_id;

        // * Creating elements
        let cardElement = document.createElement('div');
        let cardBodyElement = document.createElement('div');
        let titleElement = document.createElement('h4');
        let labelElement = document.createElement('a');
        let recordIdelement = document.createElement('a');

        // * Adding data to elements
        titleElement.innerHTML = title;
        labelElement.innerHTML = label;
        recordIdelement.innerHTML = "See full post";
        labelElement.href = `filesbylabel.html?label=${label}`;
        recordIdelement.href = `readfile.html?id=${recordId}`;

        // * Adding classes to elements for styling
        cardElement.setAttribute('class','card postCard');
        cardBodyElement.setAttribute('class','card-body postCardBody');
        titleElement.setAttribute('class','postTitle');
        labelElement.setAttribute('class','badge badge-secondary postLabel');
        recordIdelement.setAttribute('class','postReadLink');

        // * Adding elements to parent div
        let containerDiv = document.getElementById("container");
        containerDiv.appendChild(cardElement);
        cardElement.appendChild(cardBodyElement);

        cardBodyElement.appendChild(titleElement);
        titleElement.appendChild(labelElement);
        cardBodyElement.appendChild(recordIdelement);
    }    

}