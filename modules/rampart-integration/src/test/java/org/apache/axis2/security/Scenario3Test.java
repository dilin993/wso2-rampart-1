/*
 * Copyright 2004,2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.axis2.security;

import org.apache.rampart.handler.WSSHandlerConstants;
import org.apache.rampart.handler.config.InflowConfiguration;
import org.apache.rampart.handler.config.OutflowConfiguration;
import org.apache.ws.security.WSConstants;

import java.util.Hashtable;
import java.util.Properties;

/**
 * WS-Security interop scenario 3
 */
public class Scenario3Test extends InteropTestBase {


	protected OutflowConfiguration getOutflowConfiguration() {
		OutflowConfiguration ofc = new OutflowConfiguration();
		
		ofc.setActionItems("Signature Encrypt Timestamp");
		ofc.setUser("alice");
		ofc.setEncryptionUser("bob");
		ofc.setSignaturePropFile("interop.properties");
		ofc.setPasswordCallbackClass("org.apache.axis2.security.PWCallback");
		ofc.setEncryptionSymAlgorithm(WSConstants.TRIPLE_DES);
		ofc.setEncryptionKeyIdentifier(WSSHandlerConstants.SKI_KEY_IDENTIFIER);
		ofc.setSignatureKeyIdentifier(WSSHandlerConstants.BST_DIRECT_REFERENCE);
		ofc.setEnableSignatureConfirmation(false);
		
		return ofc;
	}

	protected InflowConfiguration getInflowConfiguration() {
		InflowConfiguration ifc = new InflowConfiguration();
		ifc.setActionItems("Signature Encrypt Timestamp");
		ifc.setPasswordCallbackClass("org.apache.axis2.security.PWCallback");
		ifc.setSignaturePropFile("interop.properties");
		ifc.setEnableSignatureConfirmation(false);
		return ifc;
	}

	protected String getClientRepo() {
		return SCENARIO3_CLIENT_REPOSITORY;
	}

	protected String getServiceRepo() {
		return SCENARIO3_SERVICE_REPOSITORY;
	}

	protected boolean isUseSOAP12InStaticConfigTest() {
		return true;
	}

    protected OutflowConfiguration getOutflowConfigurationWithRefs() {
        OutflowConfiguration ofc = new OutflowConfiguration();
        
        ofc.setActionItems("Signature Encrypt Timestamp");
        ofc.setUser("alice");
        ofc.setEncryptionUser("bob");
        ofc.setPasswordCallbackClass("org.apache.axis2.security.PWCallback");
        ofc.setEncryptionSymAlgorithm(WSConstants.TRIPLE_DES);
        ofc.setEncryptionKeyIdentifier(WSSHandlerConstants.SKI_KEY_IDENTIFIER);
        ofc.setSignatureKeyIdentifier(WSSHandlerConstants.BST_DIRECT_REFERENCE);
        ofc.setEnableSignatureConfirmation(false);
        
        ofc.setSignaturePropRefId("key1");
        
        return ofc;
    }

    protected InflowConfiguration getInflowConfigurationWithRefs() {
        InflowConfiguration ifc = new InflowConfiguration();
        ifc.setActionItems("Signature Encrypt Timestamp");
        ifc.setPasswordCallbackClass("org.apache.axis2.security.PWCallback");
        ifc.setEnableSignatureConfirmation(false);
        
        ifc.setSignaturePropRefId("key2");
        
        return ifc;
    }

    protected Hashtable getPropertyRefs() {
        Properties prop1 =  new Properties();
        prop1.setProperty("org.apache.ws.security.crypto.provider", "org.apache.ws.security.components.crypto.Merlin");
        prop1.setProperty("org.apache.ws.security.crypto.merlin.keystore.type", "jks");
        prop1.setProperty("org.apache.ws.security.crypto.merlin.keystore.password", "password");
        prop1.setProperty("org.apache.ws.security.crypto.merlin.file", "interop2.jks");

        Properties prop2 =  new Properties();
        prop2.setProperty("org.apache.ws.security.crypto.provider", "org.apache.ws.security.components.crypto.Merlin");
        prop2.setProperty("org.apache.ws.security.crypto.merlin.keystore.type", "jks");
        prop2.setProperty("org.apache.ws.security.crypto.merlin.keystore.password", "password");
        prop2.setProperty("org.apache.ws.security.crypto.merlin.file", "interop2.jks");
        
        Hashtable table = new Hashtable();
        table.put("key1", prop1);
        table.put("key2", prop2);
        
        return table;
    }

	
}
