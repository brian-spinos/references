/**
 * TODO: 
 *     - give better names to variables, maybe: clientPublicKey, ServerPrivateKey, sharedKey
 *     - write comments about the algorithm used
 */


class Client {
    double publickKey = 2;
    double privateKey = 3;
    double clientPubServerPri = 0;
    Server server;

    public void connectToServer(Server server){
        this.server = server;
    }

    public void sendInfoToServer(){
        double clientPubPri = Math.pow(publickKey, privateKey);
        server.getInfoFromClient(publickKey, clientPubPri);
    }

    public void getInfoFromServer(double clientPubServerPri){
        this.clientPubServerPri = clientPubServerPri;
    }

    public double calculateSharedKey(){
        double result = Math.pow(clientPubServerPri, privateKey);
        System.out.println(result);
        return result;
    }
}

class Server {
    // double publickKey = 7732; // Not used ???
    double privateKey = 5;
    Client client;
    double clientPubPri;
    double clientPub;

    public void connectToClient(Client client){
        this.client = client;
    }

    public void getInfoFromClient(double clientPub, double clientPubPri){
        this.clientPubPri = clientPubPri;
        this.clientPub = clientPub;
    }

    public void sendInfoToClient(){
        double clientPubServerPri = Math.pow(clientPub, privateKey);
        client.getInfoFromServer(clientPubServerPri);
    }

    public double calculateSharedKey(){
        double result = Math.pow(clientPubPri, privateKey);
        System.out.println(result);
        return result;
    }
}

class Main {
    public static void main(String[] args) {
        System.out.println("====== TLS 1.3 Test");

        Client client = new Client();
        Server server = new Server();

        // connect to each other
        client.connectToServer(server);
        server.connectToClient(client);

        client.sendInfoToServer();
        server.sendInfoToClient();

        double cshk = client.calculateSharedKey();
        double sshk = server.calculateSharedKey();

        if(cshk == sshk){
            System.out.println("success");
        }else{
            System.out.println("fail");
        }
    }
}

/* OUTPUT


====== TLS 1.3 Test
32768.0
32768.0
success


*/
