const nopass = document.getElementById('nopass');
const pass1 = document.getElementById('pass1');
const pass2 = document.getElementById('pass2');
//console.log(pass1);
//console.log(pass2);

function submitForm() {
    let regform = document.querySelector("#regform");
    let fd = new FormData(regform);
    let password1 = pass1.value;
    let password2 = pass2.value;
    if(password1 !== password2){
        nopass.classList.add('nopassvisible');
    }
    if(password1 === password2){
        nopass.classList.remove('nopassvisible');
        fd.delete("password2")
        console.log(fd);
        console.log(regform)
        /*document.querySelector(".passConf").innerHTML = "";*/
        /*let newform = document.createElement('form');
        newform.innerHTML = regform.innerHTML;

        newform.submit(); */
        regform.submit();
        /*fetch('/registration', {
            method: 'POST',
            body: fd
        }).then(data => {
            return data.text()
        }).then(res => {

        })*/
    }
    return false;
};
