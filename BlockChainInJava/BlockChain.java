public class BlockChain {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public int difficulty; 

    public BlockChain(int difficulty) {
        this.difficulty = difficulty;
    }
    public void addBlock(String message) {
        if (blockchain.size() == 0) {
            blockchain.add(new Block(message, "0"));
        }
        else {
            blockchain.add(new Block(message, blockchain.get(blockchain.size()-1).hash));
        }
        blockchain.get(blockchain.size()-1).mineBlock(difficulty);
    }

    public String toString() {
        System.out.println("\nThe block chain");
        for (Block block: blockchain) {
            System.out.println(block.getData());
        }

    }

    public boolean isChainValid() {

        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        for (int i =1; i<blockchain.size(); i++) {
        //compate registered and calculated hash
            currentBlock = blockchain.get(i);
            previousBlock = blockchian.get(i-1);
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                return false; 
            }

            //compare previous and stored previous hash
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                return false; 
            }

            //check if hash is solved
            if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
                return false; 
            }
        }
            return true; 
        
    }
}