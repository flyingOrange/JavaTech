package javaBasis.enumUsage;

public enum TransferStatus {
    def(-1), waiting(10), processing(20), fail(90), success(100);// 划款状态:10待划款,20划款中,,100划款成功
    private final int status;

    private TransferStatus(int type) {
        this.status = type;
    }

    public int getStatus() {
        return status;
    }

    public static TransferStatus getByStatus(int status) {
        for (TransferStatus s : TransferStatus.values()) {
            if (s.status == status) {
                return s;
            }
        }
        return null;
    }
    public static void main(String[] args) {
        //System.out.println(TransferStatus.fail.getStatus());
        
        //System.out.println(TransferStatus.getByStatus(100));
        
    }
}
