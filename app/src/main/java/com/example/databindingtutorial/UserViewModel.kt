package com.example.databindingtutorial

class UserViewModel (private var name: String, private var address : String){
     fun getName(): String{
         return name
     }
     fun setName(name: String){
         this.name=name
     }
     fun getAddress(): String{
        return address
     }
     fun setAddress(address: String){
        this.address=address
     }
}