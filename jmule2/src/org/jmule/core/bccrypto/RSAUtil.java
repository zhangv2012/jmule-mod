package org.jmule.core.bccrypto;

import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import static org.jmule.core.bccrypto.DER.*;

/**
 * utility class for converting java.security RSA objects into their
 * org.bouncycastle.crypto counterparts.
 */
public class RSAUtil
{
	
	static boolean isRsaOid(
	        DERObjectIdentifier algOid)
	    {
	        return algOid.equals(Identifiers.rsaEncryption)
	            || algOid.equals(Identifiers.id_ea_rsa);
	    }
	
    static public RSAKeyParameters generatePublicKeyParameter(
        RSAPublicKey    key)
    {
        return new RSAKeyParameters(false, key.getModulus(), key.getPublicExponent());

    }

    static public RSAKeyParameters generatePrivateKeyParameter(RSAPrivateKey    key)
    {
        if (key instanceof RSAPrivateCrtKey)
        {
            RSAPrivateCrtKey    k = (RSAPrivateCrtKey)key;

            return new RSAPrivateCrtKeyParameters(k.getModulus(),
                k.getPublicExponent(), k.getPrivateExponent(),
                k.getPrimeP(), k.getPrimeQ(), k.getPrimeExponentP(),                            k.getPrimeExponentQ(), k.getCrtCoefficient());
        }
        else
        {
            RSAPrivateKey    k = key;

            return new RSAKeyParameters(true, k.getModulus(), k.getPrivateExponent());
        }
    }
}
