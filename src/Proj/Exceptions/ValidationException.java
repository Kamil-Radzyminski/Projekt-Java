/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proj.Exceptions;

/**
 *
 * @author Kamil
 */
public class ValidationException extends Exception{
    
    public ValidationException(String errorMessage){
        super(errorMessage);
    }
}
