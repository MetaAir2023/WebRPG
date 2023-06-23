let userEffect = document.getElementsByClassName("UEImg");
let monsterEffect = document.getElementsByClassName("MEImg")
let attack = document.getElementById("attack")


attack.addEventListener("click", () => {

   if (userEffect[0].style.visibility = "hidden") {

      userEffect[0].style.visibility = "visible"
      userEffect[0].style.transform = "translate(500px)";
      setTimeout(()=>{
         userEffect[0].style.visibility = "hidden"

      },800)
   
    
      setTimeout(() => {
         userEffect[0].style.transform = "translate(-10px)"
        
      }, 2000);
      setTimeout(()=>{
         if(monsterEffect[0].style.visibility="hidden"){
            monsterEffect[0].style.visibility = "visible"
         }
      },3000)
      setTimeout(()=>{
         monsterEffect[0].style.transform='translate(-380px)'
      },4000)
      setTimeout(()=>{
       monsterEffect[0].style.visibility='hidden'  
      },4500)
      setTimeout(()=>{
         monsterEffect[0].style.transform='translate(20px)'
      },7200)
   }
   else
{
   userEffect[0].style.visibility='visible'
}
 
})
