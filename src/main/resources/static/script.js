const COMPONENT_API = "http://localhost:8080/components";
const CYCLE_API = "http://localhost:8080/cycles";
const ASSIGN_API = "http://localhost:8080/cycle-components";

// ---------------- COMPONENT ----------------

const componentForm = document.getElementById("componentForm");
const componentTableBody = document.querySelector("#componentTable tbody");
const saveButton = document.getElementById("saveButton");
const cancelButton = document.getElementById("cancelButton");

let editingComponentId = null;

// ---------------- CYCLE ----------------

const cycleForm = document.getElementById("cycleForm");
const cycleTableBody = document.querySelector("#cycleTable tbody");
const cycleSaveButton = document.getElementById("cycleSaveButton");
const cycleCancelButton = document.getElementById("cycleCancelButton");

let editingCycleId = null;

// ---------------- INITIAL LOAD ----------------

loadComponents();
loadCycles();

// ---------------- COMPONENT SAVE ----------------

componentForm.addEventListener("submit", function(e){

    e.preventDefault();

    const component = {

        name:document.getElementById("name").value,

        price:Number(document.getElementById("price").value)

    };

    if(editingComponentId==null){

        fetch(COMPONENT_API,{

            method:"POST",

            headers:{
                "Content-Type":"application/json"
            },

            body:JSON.stringify(component)

        })

        .then(()=>{

            componentForm.reset();

            loadComponents();

        });

    }

    else{

        fetch(COMPONENT_API+"/"+editingComponentId,{

            method:"PUT",

            headers:{
                "Content-Type":"application/json"
            },

            body:JSON.stringify(component)

        })

        .then(()=>{

            editingComponentId=null;

            saveButton.innerHTML="Save Component";

            componentForm.reset();

            loadComponents();

        });

    }

});

// ---------------- LOAD COMPONENTS ----------------

function loadComponents(){

    fetch(COMPONENT_API)

    .then(res=>res.json())

    .then(data=>{

        componentTableBody.innerHTML="";

        document.getElementById("componentCount").innerHTML=data.length;

        const componentSelect=document.getElementById("componentSelect");

        componentSelect.innerHTML="";

        data.forEach(component=>{

            componentTableBody.innerHTML+=`

            <tr>

            <td>${component.id}</td>

            <td>${component.name}</td>

            <td>₹${component.price}</td>

            <td>

            <button onclick="editComponent(${component.id},'${component.name}',${component.price})">

            Edit

            </button>

            <button onclick="deleteComponent(${component.id})">

            Delete

            </button>

            </td>

            </tr>

            `;

            componentSelect.innerHTML+=`

            <option value="${component.id}">

            ${component.name}

            </option>

            `;

        });

    });

}

// ---------------- EDIT COMPONENT ----------------

function editComponent(id,name,price){

    editingComponentId=id;

    document.getElementById("name").value=name;

    document.getElementById("price").value=price;

    saveButton.innerHTML="Update Component";

}

// ---------------- DELETE COMPONENT ----------------

function deleteComponent(id){

    if(!confirm("Delete Component?")){

        return;

    }

    fetch(COMPONENT_API+"/"+id,{

        method:"DELETE"

    })

    .then(()=>{

        loadComponents();

    });

}

// ---------------- CANCEL COMPONENT ----------------

cancelButton.addEventListener("click",function(){

    editingComponentId=null;

    componentForm.reset();

    saveButton.innerHTML="Save Component";

});

// ---------------- SEARCH ----------------

document.getElementById("searchComponent").addEventListener("keyup",function(){

    const value=this.value.toLowerCase();

    const rows=document.querySelectorAll("#componentTable tbody tr");

    rows.forEach(row=>{

        row.style.display=row.innerText.toLowerCase().includes(value)?"":"none";

    });

});

// ---------------- CYCLE SAVE ----------------

cycleForm.addEventListener("submit",function(e){

    e.preventDefault();

    const cycle={

        name:document.getElementById("cycleName").value

    };

    if(editingCycleId==null){

        fetch(CYCLE_API,{

            method:"POST",

            headers:{

                "Content-Type":"application/json"

            },

            body:JSON.stringify(cycle)

        })

        .then(()=>{

            cycleForm.reset();

            loadCycles();

        });

    }

    else{

        fetch(CYCLE_API+"/"+editingCycleId,{

            method:"PUT",

            headers:{

                "Content-Type":"application/json"

            },

            body:JSON.stringify(cycle)

        })

        .then(()=>{

            editingCycleId=null;

            cycleSaveButton.innerHTML="Save Cycle";

            cycleForm.reset();

            loadCycles();

        });

    }

});

// ---------------- LOAD CYCLES ----------------

function loadCycles(){

    fetch(CYCLE_API)

    .then(response=>response.json())

    .then(data=>{

        cycleTableBody.innerHTML="";

        document.getElementById("cycleCount").innerHTML=data.length;

        const cycleSelect=document.getElementById("cycleSelect");

        const priceCycleSelect=document.getElementById("priceCycleSelect");

        cycleSelect.innerHTML="";

        priceCycleSelect.innerHTML="";

        data.forEach(cycle=>{

            cycleTableBody.innerHTML+=`

            <tr>

                <td>${cycle.id}</td>

                <td>${cycle.name}</td>

                <td>

                    <button onclick="editCycle(${cycle.id},'${cycle.name}')">

                    Edit

                    </button>

                    <button onclick="deleteCycle(${cycle.id})">

                    Delete

                    </button>

                </td>

            </tr>

            `;

            cycleSelect.innerHTML+=`

            <option value="${cycle.id}">

            ${cycle.name}

            </option>

            `;

            priceCycleSelect.innerHTML+=`

            <option value="${cycle.id}">

            ${cycle.name}

            </option>

            `;

        });

    });

}

// ---------------- EDIT CYCLE ----------------

function editCycle(id,name){

    editingCycleId=id;

    document.getElementById("cycleName").value=name;

    cycleSaveButton.innerHTML="Update Cycle";

}

// ---------------- DELETE CYCLE ----------------

function deleteCycle(id){

    if(!confirm("Delete this cycle?")){

        return;

    }

    fetch(CYCLE_API+"/"+id,{

        method:"DELETE"

    })

    .then(()=>{

        loadCycles();

    });

}

// ---------------- CANCEL CYCLE ----------------

cycleCancelButton.addEventListener("click",function(){

    editingCycleId=null;

    cycleForm.reset();

    cycleSaveButton.innerHTML="Save Cycle";

});

// ---------------- ASSIGN COMPONENT ----------------

document.getElementById("assignButton").addEventListener("click",function(){

    const cycleId=document.getElementById("cycleSelect").value;

    const componentId=document.getElementById("componentSelect").value;

    fetch(ASSIGN_API,{

        method:"POST",

        headers:{

            "Content-Type":"application/json"

        },

        body:JSON.stringify({

            cycleId:cycleId,

            componentId:componentId

        })

    })

    .then(response=>{

        if(response.ok){

            alert("Component Assigned Successfully");

        }

        else{

            alert("Assignment Failed");

        }

    });

});

// ---------------- PRICE BREAKDOWN ----------------

document.getElementById("priceButton").addEventListener("click",function(){

    const cycleId=document.getElementById("priceCycleSelect").value;

    fetch(CYCLE_API+"/"+cycleId+"/price")

    .then(response=>response.json())

    .then(data=>{

        let html="";

        html+="<h3>"+data.cycleName+"</h3>";

        html+="<h4>Components</h4>";

        html+="<ul>";

        let total=0;

        data.components.forEach(component=>{

            html+="<li>"+component+"</li>";

        });

        html+="</ul>";

        html+="<div class='totalPrice'>";

        html+="Total Price : ₹"+data.totalPrice;

        html+="</div>";

        document.getElementById("dashboardPrice").innerHTML="₹"+data.totalPrice;

        const result=document.getElementById("priceResult");

        result.innerHTML=html;

        result.style.display="block";

    })

    .catch(()=>{

        alert("Price not found");

    });

});