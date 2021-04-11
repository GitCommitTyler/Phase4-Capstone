var myalbums = [];
var mytracks = [];

var total=0;
mytracks=JSON.parse(localStorage.getItem("tracks"));
myalbums=JSON.parse(localStorage.getItem("albums"));
if(mytracks==null){
   localStorage.setItem("tracks","[]");
}
if(myalbums==null){
   localStorage.setItem("albums","[]");
}

function clearCart(){
   var temp=[];
   localStorage.setItem("tracks",JSON.stringify(temp));
   localStorage.setItem("albums",JSON.stringify(temp));
}
function getTotal(){
   total=0;
   for(var i in mytracks){
      total+=parseFloat(mytracks[i].price);
   }
   for(var i in myalbums){
      total+=parseFloat(myalbums[i].price);
   }
   console.log(total);
   return total;
}

function doShowAll() {
   var cards;
   total=getTotal();
      console.log("showing cart");
      console.log(mytracks);
      console.log(myalbums);
       //For a more advanced feature, you can set a cap on max items in the cart.
       for (i = 0; i < mytracks.length; i++) {
        cards+="<div class='card'>"+
           '<div class="card-body">'+
              '<h5 class="card-title">'+mytracks[i].name+'</h5>'+
              '<h6 class="card-subtitle mb-2 text-muted">Single</h6>'+
              '<p class="card-text">$'+mytracks[i].price+'</p>'+
              '<button id=id'+mytracks[i].trackid+' onclick="RemoveTrack(id)">Remove</button>'+
           '</div>'+
        '</div></br>';
       }
       for (i = 0; i < myalbums.length; i++) {
         cards+="<div class='card'>"+
            '<div class="card-body">'+
               '<h5 class="card-title">'+myalbums[i].name+'</h5>'+
               '<h6 class="card-subtitle mb-2 text-muted">Album</h6>'+
               '<p class="card-text">$'+myalbums[i].price+'</p>'+
               '<button id=id'+myalbums[i].albumid+' onclick="RemoveAlbum(id)">Remove</button>'+
            '</div>'+
         '</div></br>';
        }
       //Bind the data to HTML table.
       
       var checkoutButton='</br><button type="button" onclick="/user/checkout">Checkout</button>';
       document.getElementById("total").innerHTML="Total=$"+getTotal().toFixed(2);
       if(cards !=null){
         document.getElementById("total").innerHTML="Total=$"+getTotal().toFixed(2)+checkoutButton;
       }
       else{
          cards='<h3>Empty</h4>';
       }
       document.getElementById('cart').innerHTML = cards;
}
function isAlbuminCart(id){
   for(var i in myalbums){
      if(myalbums[i].albumid == id){
         console.log("Album already in cart");
         return true;
      }
   }
   console.log("track is not in cart")
   return false;
}
function isTrackinCart(id){
   for(var i in mytracks){
      if(mytracks[i].trackid == id){
         console.log("track already in cart");
         return true;
      }
   }
   console.log("track is not in cart")
   return false;
}
function AddTrack(id) {
  var track =document.querySelector("#"+id);
  var trackprice =track.dataset.price;
  var trackname=track.dataset.track;
  var newid=id.substring(2);
  console.log("Add track function called");
  if(isTrackinCart(newid)){
  }else{
   console.log("Adding Track to shopping Cart");
   console.log("Track id:"+newid+" TrackName:"+trackname+" TrackPrice:"+trackprice);
   mytracks.push({"trackid":newid,"price":trackprice,"name":trackname});
   localStorage.setItem("tracks",JSON.stringify(mytracks));
   total+=parseFloat(trackprice);
  }
}
function AddAlbum(id) {
   var album =document.querySelector("#"+id);
   var albumprice =album.dataset.price;
   var albumname=album.dataset.album;
   var newid=id.substring(2);

   console.log("Add Album function called");
   if(isAlbuminCart(newid)){
   }else{
    console.log("Adding Album to shopping Cart");
    console.log("Album id:"+newid+" AlbumName:"+albumname+" AlbumPrice:"+albumprice);

    myalbums.push({"albumid":newid,"price":albumprice,"name":albumname});
    localStorage.setItem("albums",JSON.stringify(myalbums));
    total+=parseFloat(albumprice);
   }
 }

function RemoveTrack(id){
   var newid=id.substring(2);
   console.log("Called Remove Track Function on trackid:"+newid);
   for(var i in mytracks){
      if(newid==mytracks[i].trackid){
         console.log("Found Track to remove");
         mytracks.splice(i,1);
         localStorage.setItem("tracks",JSON.stringify(mytracks));
      }
   }
   doShowAll();
}
function RemoveAlbum(id){
   var newid=id.substring(2);
   console.log("Called Remove Track Function on trackid:"+newid);
   for(var i in myalbums){
      if(newid==myalbums[i].albumid){
         console.log("Found Track to remove");
         myalbums.splice(i,1);
         localStorage.setItem("albums",JSON.stringify(myalbums));
      }
   }
   doShowAll();
}

function openNav() {
   document.getElementById("mySidebar").style.width = "250px";
   document.getElementById("main").style.marginLeft = "250px";
   doShowAll();
 }
 
 function closeNav() {
   document.getElementById("mySidebar").style.width = "0";
   document.getElementById("main").style.marginLeft= "0";
 }