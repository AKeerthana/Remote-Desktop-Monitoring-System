/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_project;

/**
 *
 * @author USER
 */
class CryptoException extends Exception {

    public CryptoException() {
    }
    public CryptoException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
