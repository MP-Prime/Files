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
                postData(data);
            }) 
            .catch((error) => console.log(`Error ${error}`));
        })

        // * Display fetch by id
        function postData(data){
            
            // * Testing
            // console.log(data.title);
            // console.log(data.content);
            // console.log(data.label);
            // console.log(data.record_id);

            // * Getting data
            let title = data.title;
            let content = data.content;
            let label = data.label;
            let recordId = data.record_id;

            // * Creating elements
            let cardElement = document.createElement('div');
            let cardBodyElement = document.createElement('div');
            let titleElement = document.createElement('h4');
            let contentElement = document.createElement('p');
            let labelElement = document.createElement('a');
            let updateElement = document.createElement('a');
            let deleteElement = document.createElement('button');

            // * Adding data to elements
            titleElement.innerHTML = title;
            labelElement.innerHTML = label;
            contentElement.innerHTML = content;
            updateElement.innerHTML = "Update file";
            deleteElement.innerHTML = "Delete file";
            labelElement.href = `filesbylabel.html?label=${label}`;
            updateElement.href = `editfile.html?id=${recordId}`;

            // * Adding classes to elements for styling
            cardElement.setAttribute('class','card');
            cardBodyElement.setAttribute('class','card-body postCardBody');
            titleElement.setAttribute('class','postTitle');
            labelElement.setAttribute('class','badge badge-secondary postLabel');
            updateElement.setAttribute('class','btn btn-primary');
            deleteElement.setAttribute('class','btn btn-danger');
            deleteElement.id = "delBtn";
            updateElement.id = "updBtn";

            // * Adding elements to parent div
            let containerDiv = document.getElementById("container");
            containerDiv.appendChild(cardElement);
            cardElement.appendChild(cardBodyElement);

            cardBodyElement.appendChild(titleElement);
            titleElement.appendChild(labelElement);
            cardBodyElement.appendChild(updateElement);
            cardBodyElement.appendChild(deleteElement);
            cardBodyElement.appendChild(contentElement);
            
            deleteElement.addEventListener("click", Delete);
                

        }

        // * Delete 
        // Deleting a specific file
        function Delete() {
            fetch(`http://localhost:8091/livefiles/delete/${id}`,{
                method:`DELETE`
            })
            .then(location.replace("success.html"))
            .catch((error) => console.log(error));
        }
    }
