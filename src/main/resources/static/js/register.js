const apiUrl = "/api/register";


const firstNameIn = document.getElementById("firstName");
const lastNameIn = document.getElementById("lastName");
const userNameIn = document.getElementById("userName");
const passwordIn = document.getElementById("password");
const confirmPasswordIn = document.getElementById("confirmPassword");
const registerBtn = document.getElementById("register-btn")



registerBtn.addEventListener("click", ()=> createUser())

async function createUser(){
    const firsName =firstNameIn.value.trim();
    const lastName =lastNameIn.value.trim();
    const userName = userNameIn.value.trim();
    const password = passwordIn.value.trim();
    const confirmPassword = confirmPasswordIn.value.trim();


    if(firstNameIn.value.trim() ==="" || lastNameIn.value.trim() ==="" || userNameIn.value.trim() ==="" || passwordIn.value.trim() || confirmPasswordIn.value.trim() === ""){
        const message = "all fields are mandatory"
        showNotification(message,"error")
    }
    else{
        if(passwordIn.value.trim() != confirmPasswordIn.value.trim()){
            const message="confirm password mismatch";
            showNotification(message,"error")
        }
        else{
            const response = await fetch(apiUrl,{
                method:"POST",
                headers:{"Content-Type": "application/json"},
                body: JSON.stringify({
                    firsName : firsName,
                    lastName :lastName,
                    userName : userName,
                    password : password
                })
            })

            if(!response.ok){
                const data = await response.json();
                const message= data.error;

                showNotification(message,"error");

            }
        }
    }

    firsName="";
    lastName="";
    userName="";
    password="";
    confirmPassword="";

}


function showNotification(message, duration = 2000, type = "success") {
  const notification = document.getElementById("notification");
  notification.textContent = message;

  // You can change color based on type
  if(type === "success") notification.style.backgroundColor = "#4caf50";
  else if(type === "error") notification.style.backgroundColor = "#f44336";

  notification.classList.add("show");

  // Remove after duration
  setTimeout(() => {
    notification.classList.remove("show");
  }, duration);
}


