/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
const getCookies = function(){
        let pairs = document.cookie.split(";");
        console.log(pairs);
        let cookies = {};
        for (let i=0; i<pairs.length; i++){
            let pair = pairs[i].split("=");
            cookies[pair[0]] = unescape(pair[1]);
        }
        console.log(cookies.name);
        return cookies.name;
};

//if (no cookie)
  // find by id "up"
  // set disabled
  // find by id "login_register"
  // set text "please <a href="">login</a> or register to upload images"

if(typeof getCookies()==="undefined"){
    const up = document.querySelector("#up");
    const span = document.querySelector("#login_register");
    const spancomment = document.querySelector("#login_check");
    up.disabled = true;
    //var a = document.createElement("a");
    //var node = document.createTextNode("login");
    //a.appendChild(node);
    try{
        if (document.querySelector('head title').innerHTML === 'Home'){
            spancomment.innerHTML="Please <a href=login.html>login<a> or <a href=signin.html>register<a> to comment";
        }
        if (document.querySelector('head title').innerHTML === 'Add images'){
            span.innerHTML="Please <a href=login.html>login<a> or <a href=signin.html>register<a> to upload images";
        }
    } catch(e){
        
    }
}



   
