const getCookie = function(){
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

if(typeof getCookie()!=="undefined"){
    const profilenav = document.querySelector("#loginprofile");
    try{
        profilenav.innerHTML=getCookie();
        
    } catch(e){
        
    }
}
if(typeof getCookie()==="undefined"){
    const h2 = document.querySelector("#h2")
    try{
        h2.innerHTML = "&nbsp;&nbsp;Not logged in";
    } catch(e){
        
    }
}

const getComments = (id) => {
    let jsonArray = [];
    
    const commentdiv = document.getElementById('commentscroll')
    while (commentdiv.hasChildNodes()) {
        commentdiv.removeChild(commentdiv.lastChild);
    }
    fetch('http://10.114.32.124:8080/database/CommentServlet?id='+id)
    //fetch('http://10.114.32.124:8080/database/CommentServlet')
            .then( (response) => {
                return response.json();
            })
            .then((data) => {
                //console.log(data);
                data.commentList.forEach(function (jsondata) {
                   //console.log(jsondata.comment)
                   jsonArray.push(jsondata);
                });
                jsonArray.forEach(function(obj) { 
                      //console.log(obj);
                      let p = document.createElement("p");
                      let node = document.createTextNode(obj[0]+" said: "+obj[2]);
                      p.appendChild(node);
                      commentdiv.appendChild(p);
                });
            });    
};

let showModal = false;
const getAllImages = () => {
    const ul = document.querySelector('ul')
    let jsonArray = [];  
    fetch('http://10.114.32.124:8080/database/do')
            .then( (response) => {
                return response.json();
            })
            .then((data) => {
                //console.log(data);
                data.mediaList.forEach(function (jsondata) {
                   //console.log(jsondata.URL);
                   //console.log(jsondata.description)
                   jsonArray.push(jsondata);
                });
                let html = '';
                jsonArray.forEach(function(obj) { 
                    //console.log(obj);
                    
                    // obj on url
                      html +=
                          `<li>
                          <figure>
                            <p>${obj.description}</p>
                            <a href="${obj.URL}" data-cid="${obj.id}"><img src="${obj.URL}"></a>
                          </figure>
                        </li>`;
                });
                
                    ul.innerHTML = html;
                    linkActions();
                    
            });    
};

if (document.querySelector('head title').innerHTML === 'Home' || document.querySelector('head title').innerHTML === 'Favourites'){
getAllImages();
};

const linkActions = () => {
  //select all a elements inside ul
  const a = document.querySelectorAll('ul a');
  //loop links
  a.forEach((link) => {
    //when link is clicked
    link.addEventListener('click', (event) => {
      
      //prevent default action
      event.preventDefault();

      //get data-cid value of link
      const cid = link.dataset.cid;
      //set modal image src to href value
      const modal = document.getElementById('modal');
      const image = modal.querySelector('img');
      modal.querySelector('#comid').value = cid;
      getComments(cid);
      image.setAttribute('src', event.target.parentElement.getAttribute('href'));
      //display modal (use lightbox class)
      //replace
      modal.classList.replace('hidden', 'lightbox');
      showModal = true;
      dimmedBackground();
    });
  });
};

if(document.querySelector('head title').innerHTML === 'Home' || document.querySelector('head title').innerHTML === 'Favourites'){
    const close = document.querySelector('#close');
    close.addEventListener('click', (evt) => {
        let modal = document.getElementById('modal');
        modal.classList.replace('lightbox', 'hidden');
        showModal=false;
        dimmedBackground();
    });
}

const dimmedBackground = () => {
    const div = document.getElementById('dimmingBackground');
    if(showModal){
        div.classList.add('dimming');
    }
    else {
        div.classList.remove('dimming');
    }
};

//---------------------

let formOK = 0;

const inputs = document.querySelectorAll('input');

const checkAttribute = (elements, attr, func) => {
    elements.forEach((element) => {
        if (element.hasAttribute(attr)) {
            func(element);
        }
    });
};



const checkPattern = (element) => {
    const pattern = new RegExp(element.getAttribute('pattern'));
    const value = element.value;
    if (!pattern.exec(value)) {
        formOK++;
        element.setAttribute('style', 'border: red solid 1px')
    }
    else {
        formOK--;
        element.removeAttribute('style');
    }
};


const form = document.querySelector('form');


if (document.querySelector('title').innerHTML === "Sign in"){
    form.addEventListener('submit', (evt) => {
        evt.preventDefault();
        formOK = 0;
        checkAttribute(inputs, 'pattern', checkPattern);
        console.log(formOK);
        if (formOK === -4){
            check_password_match(document.getElementById('confirm'));
        }
    });
}

if (document.querySelector('title').innerHTML === "Login"){
    form.addEventListener('submit', (evt) => {
        evt.preventDefault();
        formOK = 0;
        checkAttribute(inputs, 'pattern', checkPattern);
        console.log(formOK);
        if (formOK === -2){
            form.submit();
        }
    });
}



const check_password_match = (input) => {
    if (input.value !== document.getElementById('password').value) {
        input.setAttribute('style', 'border: red solid 1px')
    } else {
        input.removeAttribute('style');
        form.submit();
    }
};


// MENU BUTTON

const closeNavButton = document.querySelector('#menuclose');
const openNavButton = document.querySelector('#menuopen');
const closeEverythingButton = document.querySelector('#ul');
const showSearchBarButton = document.querySelector('#searchIcon');
const showProfileButton = document.querySelector('#loggedInIcon');
const closeProfileButton = document.querySelector('#profileclose');

const openNav = (evt) => {
    evt.preventDefault();
    document.getElementById("menu").style.width = "200px";
};

const closeNav = (evt) => {
    evt.preventDefault();
    document.getElementById("menu").style.width = "0";
};

// SEARCH BAR


const showSearchBar = (evt) => {
    evt.preventDefault();
    document.getElementById("search").style.visibility = "visible";
    document.getElementById("searchIcon").style.visibility = "hidden";
};

const closeSearchBar = (evt) => {
    evt.preventDefault();
    document.getElementById("search").style.visibility = "hidden";
    document.getElementById("searchIcon").style.visibility = "visible";
};



// PROFILE BUTTON

const showProfile = (evt) => {
    evt.preventDefault();
    console.log(document.getElementById("profilenav").style.height.value);
    document.getElementById("profilenav").style.height = "4rem";
};

const closeProfile = (evt) => {
    evt.preventDefault();
    document.getElementById("profilenav").style.height = "0";
};

//CLOSE EVERYTHING

const closeEverything = (evt) => {
    closeNav(evt);
    closeSearchBar(evt);
    closeProfile(evt);
};

closeNavButton.addEventListener("click", closeNav);
openNavButton.addEventListener("click", openNav);
showSearchBarButton.addEventListener("click", showSearchBar);
showProfileButton.addEventListener("click", showProfile);
closeProfileButton.addEventListener("click", closeProfile);
if (document.getElementById("searchIcon").style.visibility === "hidden"){}
else {
    closeEverythingButton.addEventListener("click", closeEverything);
}

// SCROLL BUTTON

window.onscroll = a = () => {scroll()};

const scrollButton = document.querySelector('#scroll');
const scroll = () => {
    if (document.documentElement.scrollTop > 0) {
        document.getElementById("scroll").style.display = "block";
    } else {
        document.getElementById("scroll").style.display = "none";
    }
};

const toTheTop = () => {
    document.documentElement.scrollTop = 0;
};

scrollButton.addEventListener("click", toTheTop);



//SEARCH BAR

document.getElementById('search').addEventListener('keypress', function (e) {
    let key = e.which || e.keyCode;
    if (key === 13) {
        e.preventDefault();
        let headword = document.getElementById('search').value.toLowerCase();
        document.getElementById('search').value = '';
        search(headword);
    }
});

const search = (headword) => {
    const elements= document.querySelectorAll('figure p');
    elements.forEach((element) => {
        if (element.innerHTML.toLowerCase() === headword) {
            element.scrollIntoView({block:'center'});
        }
    });
};
