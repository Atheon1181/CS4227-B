package Interceptor;

//"encrypts" password, then compares to existing "encrypted" passwords in file
class pseudoEncrypt {
     String encrypt(String password){
         return password.toUpperCase();
    }
}
