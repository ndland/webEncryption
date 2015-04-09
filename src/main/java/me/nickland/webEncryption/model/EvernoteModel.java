package me.nickland.webEncryption.model;

import com.evernote.auth.EvernoteAuth;
import com.evernote.auth.EvernoteService;
import com.evernote.clients.ClientFactory;
import com.evernote.clients.NoteStoreClient;
import com.evernote.edam.error.EDAMSystemException;
import com.evernote.edam.error.EDAMUserException;
import com.evernote.edam.type.Notebook;
import com.evernote.thrift.TException;

import java.util.List;

/**
 * Created by Nick on 4/8/15.
 */
public class EvernoteModel {

    public void connectToEvernote() {
        try {
            String developerToken = "S=s1:U=90a5c:E=153ee65e425:C=14c96b4b700:P=1cd:A=en-devtoken:V=2:H=2526ff37f08460acaecccc1d753b07d2";

            // Set up the NoteStore client
            EvernoteAuth evernoteAuth = new EvernoteAuth(EvernoteService.SANDBOX, developerToken);
            ClientFactory factory = new ClientFactory(evernoteAuth);
            NoteStoreClient noteStore = factory.createNoteStoreClient();

            // Make API calls, passing the developer token as the authenticationToken param
            List<Notebook> notebooks = noteStore.listNotebooks();

            for (Notebook notebook : notebooks) {
                System.out.println("Notebook: " + notebook.getName());
            }
        } catch (EDAMUserException e) {
            e.printStackTrace();
        } catch (EDAMSystemException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
    }
}
