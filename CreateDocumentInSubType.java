package ext.pdmlink;

/**
 * @formatter:off
 * Last Changed    ::                      $Date: 14-Jun-2019 07:12:54 (IST) $:
 * Last Changed By ::                      $Author: Soumya $:
 * Last Changed Rev::                      $Rev: 1 $:
 * @formatter:on
**/

import com.ptc.core.foundation.type.server.impl.TypeHelper;
import com.ptc.core.meta.common.impl.WCTypeIdentifier;

import wt.doc.WTDocument;
import wt.fc.PersistenceHelper;
import wt.folder.Folder;
import wt.folder.FolderHelper;
import wt.inf.container.WTContainerHelper;
import wt.inf.container.WTContainerRef;
import wt.method.RemoteAccess;
import wt.method.RemoteMethodServer;
import wt.type.TypeDefinitionReference;
import wt.type.TypedUtilityServiceHelper;
import wt.util.WTException;

/**
 * 
 * @author 40002294(Soumya Ranjan Biswal)
 * Create WTDocument in subtype of WTDocument
 *
 */

public class CreateDocumentInSubType implements RemoteAccess {

	public static void main(String[] args) throws Exception   {
		
		/**
		 * 
		 * @rms (Remote Method Server) Start
		 *
		 */
		
		RemoteMethodServer rms= RemoteMethodServer.getDefault();
		rms.setUserName("wcadmin");
		rms.setPassword("wcadmin");
		System.out.println("logged in Windchill");    
		Class<?> argTypes[]={String.class};
		Object argValues[]={"soumya"};
		try {
			System.out.println("Call to Create Document Method");
			rms.invoke("createDocument", "ext.pdmlink.CreateDocument", null, argTypes, argValues); 
		}catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void createDocument(String str)throws Exception {
		
		try {
			System.out.println("Method Start");
			
			/**
			 * 
			 * Variable Declaration
			 *
			 */
			
			String docNumber="Test1234";
			String docName="Test1234";
			String desription="this is custom documnet created by soumya";
			String organization="Demo Organization";
			String product="GOLF_CART";
			String containerPath = "/wt.inf.container.OrgContainer="+organization+"/wt.pdmlink.PDMLinkProduct="+product;
			String folderName="VSATestFolder";
			String folderPath = "/Default/"+folderName;
			WCTypeIdentifier typeIdentifier =null;
			TypeDefinitionReference typeReference=null;
			WTContainerRef containerRef=null;
			WTDocument doc=null;
			Folder folder=null;
			
			/**
			 * 
			 * Execution Start From Here
			 *
			 */
			typeIdentifier = (WCTypeIdentifier) TypeHelper.getTypeIdentifier("WCTYPE|wt.doc.WTDocument|com.lnties.customSubtype");;
			typeReference = TypedUtilityServiceHelper.service.getTypeDefinitionReference(typeIdentifier.getTypename());
			containerRef = WTContainerHelper.service.getByPath(containerPath);
			folder = FolderHelper.service.getFolder(folderPath,containerRef);
			doc = WTDocument.newWTDocument(); 
			
			/**
			 * 
			 * Set WTDocument All Attribute 
			 *
			 */
			
			doc.setName(docName);
			doc.setNumber(docNumber); 
			doc.setDescription(desription);
			doc.setContainerReference(containerRef);
			doc.setTypeDefinitionReference(typeReference);
			FolderHelper.assignLocation(doc, folder);
			
			/**
			 * 
			 * Store WTDocument In Windchill Database
			 *
			 */
			
			PersistenceHelper.manager.store(doc);
			System.out.println("Method end");
		}catch(WTException e) {
			e.printStackTrace();
		}
	}
}
