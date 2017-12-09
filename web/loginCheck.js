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

if(typeof getCookies()!=="undefined"){
    const loginid = document.querySelector("#loginid");
    const loginspan = document.querySelector("#notification");
    loginid.disabled = true;
    
    try{
        if (document.querySelector('head title').innerHTML === 'Login'){
            loginspan.innerHTML="You are already logged in";
        }
    } catch(e){
        
    }
}

