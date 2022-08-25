## Inspeções estáticas com Sonar  
## Aluno: Alexandre de Mesquita Fabian  
## Professor: Rodrigo Prestes Machado

### Utilizado Projeto https://github.com/amfabian/pw2-projeto-final
Arquivos do web service BFF em https://github.com/amfabian/pw2-projeto-final/tree/main/bff/src/main/java/dev/ifrs  


# Unnecessary imports should be removed (java:S1128)  

Code Smell: Minor  
The imports part of a file should be handled by the Integrated Development Environment (IDE), not manually by the developer.

Unused and useless imports should not occur if that is the case.

Leaving them in reduces the code’s readability, since their presence can be confusing.

>    ### Exemplo:  
>    linhas 06, 07 e 22 de [/backendclients/UsuarioBC.java](https://github.com/amfabian/pw2-projeto-final/blob/main/bff/src/main/java/dev/ifrs/backendclients/UsuarioBC.java):  
>   `import javax.annotation.security.RolesAllowed;`  
>   `import javax.transaction.Transactional;`  
>    linhas 17 e 19 de [/UsuarioBFF.java](https://github.com/amfabian/pw2-projeto-final/blob/main/bff/src/main/java/dev/ifrs/UsuarioBFF.java):  
>   `import javax.ws.rs.core.Context;`  
>   `import javax.ws.rs.core.SecurityContext;`  
>   *Resolução:*   
>    linhas suprimidas do código.


# Standard outputs should not be used directly to log anything (java:S106)  

Code Smell: Major  
When logging a message there are several important requirements which must be fulfilled:  

The user must be able to easily retrieve the logs
The format of all logged message must be uniform to allow the user to easily read the log
Logged data must actually be recorded
Sensitive data must only be logged securely
If a program directly writes to the standard outputs, there is absolutely no way to comply with those requirements. That’s why defining and using a dedicated logger is highly recommended.


>   ### Exemplo:  
>   linha 102 de [/UsuarioBFF.java](https://github.com/amfabian/pw2-projeto-final/blob/main/bff/src/main/java/dev/ifrs/UsuarioBFF.java):  
>   `System.out.println("BFF DELETE OK ");`  
>   *substituida por:*  
>   `String message = "User deletado " + id.toString(); `   
>    `LOGGER.fine(message);`
    
# Field names should comply with a naming convention (java:S116)    

Code Smell: Minor  
Sharing some naming conventions is a key point to make it possible for a team to efficiently collaborate. This rule allows to check that field names match a provided regular expression.

>   ### Exemplo:  
>   linha 06 de [/model/Mensagem.java](https://github.com/amfabian/pw2-projeto-final/blob/main/bff/src/main/java/dev/ifrs/model/Mensagem.java):  
>   `private String user_id;`  
>   *substituida por:*  
>   `private String userId;`  
