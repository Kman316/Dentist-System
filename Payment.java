import java.util.*;



public class Payment implements java.io.Serializable{
		static int paymentNoCount = 1;

		private int paymentNo = paymentNoCount;
		private double paymentAmt;
		private String paymentDate;


		public Payment(){

		}

		public Payment(double paymentAmt,String paymentDate){
			this.paymentAmt=paymentAmt;
			this.paymentDate=paymentDate;
			paymentNoCount++;
		}

		public double getPaymentAmt() {
			return paymentAmt;
		}

		public void setPaymentAmt(double paymentAmt) {
			this.paymentAmt = paymentAmt;
		}

		public String getPaymentDate() {
			return paymentDate;
		}

		public void setPaymentDate(String paymentDate) {
			this.paymentDate = paymentDate;
		}

		public int getPaymentNo() {
			return paymentNo;
		}

		public void setPaymentNo(int paymentNo){
			this.paymentNo = paymentNoCount;
		}

		public String toString() {
			return "Payment Number: " + paymentNo + "\nPayment Amount: "+ paymentAmt + "\nPayment Date: " + paymentDate;
		}

		public void print(){
			System.out.println("Payment Number: " + paymentNo + "\nPayment Amount: "+ paymentAmt + "\nPayment Date: " + paymentDate);
		}


}
