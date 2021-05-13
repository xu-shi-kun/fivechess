package day1026;

import java.util.Scanner;

//��������Ϸ
//1)���̵Ļ���
//2)�ڷ��Ͱ׷����� 
//3)�����Ӷ�Ӧ��λ�ý������޸�Ϊ@  0
//4)�ж�ʤ��
//5)�ж��ظ����ӵ�����
public class FiveChessGame {
	//�����ά����,��ʾ��������
	static char[][] chesses=new char[17][17];
	
	public static void main(String[] args) {
		//����������̵�����
		fillChess();
		
		//����������̵�����
		printChess();
		
		Scanner sc=new Scanner(System.in);
		boolean flag=true;//����
		//flagΪtrue�ڷ�ִ�� 
		//flagΪfalse�׷�ִ��
		for(;;){  //��ѭ��
			if(flag){  //���ƺڷ�����
				//�ڷ�����
				System.out.println("��ڷ�����:");
				String strBlack=sc.next(); //34
				System.out.println("�ڷ����������:"+strBlack);
				//���ݺڷ����ӵ�λ�ý�*�޸�Ϊ@
				//chesses[4][5]='@'; //��ʱ��
				char bC1=strBlack.charAt(0);
				char bC2=strBlack.charAt(1);
				//�ж��ظ�����
				int m=isChess(bC1,bC2);
				if(m==1){ //�ظ�������
					flag=true;
					System.out.println("�ظ�������");
				}else{
					updateBlack(bC1,bC2);
					//�����������
					printChess();
					//�жϺڷ��Ƿ�ʤ��
					boolean f1=isBlackWin();
					if(f1==true){
						System.out.println("�ڷ�ʤ��,��Ϸ����");
						System.exit(0);
					}
					flag=false;
				}
				
			}
			
			if(!flag){ //���ư׷�����
				//�׷�����
				System.out.println("��׷�����:");
				String strWhite=sc.next(); //a1
				System.out.println("�׷����������:");
				//���ݰ׷����ӵ�λ�ý�*�޸�Ϊ0
				chesses[11][2]='0';
				//�����������
				printChess();
				flag=true;
			}
			
			
			
			
			
			
		}
		
	
	
	}
	
	//����һ������ -- ����������̵�����
	public static void printChess(){
		//����������̵�����
		for(int i=0;i<chesses.length;i++){
			for(int j=0;j<chesses[i].length;j++){
				System.out.print(chesses[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	//����һ������-- ����������̵�����
	public static void fillChess(){
		//  0 1 2 3 4 5 6 7 8 9 a b c d e f
		//0 * * * * * * * * * * * * * * * *
		//1 * * * * * * * * * * * * * * * *
		//2 * * * * * * * * * * * * * * * *
		//����������̵�����
		char[] c={' ','0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		//����������̵ĵ�0��
		for(int n=0;n<c.length;n++){//n=0~16
			chesses[0][n]=c[n];
		}
		//������̵ĵ�1��~��16��
		for(int i=1;i<chesses.length;i++){//i=1~16
			//����0��
			chesses[i][0]=c[i];
			//�����ǵ�1��~��16��
			for(int j=1;j<chesses[i].length;j++){
				chesses[i][j]='*';
			}
		}
	}
	
	//����һ������ --���ݺڷ���������У��ҵ�
	//ʵ�ʵ����±�����±�,��*�޸�Ϊ@
	//bC1 a bC2 3
	public static void updateBlack(char bC1,char bC2){
		char[] c={' ','0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		//�±�          0   1   2   3   4                                               15
		//bC1��bC2�Ǻڷ�������� ��
		int x=0; //���±�
		int y=0; //���±�
		for(int n=0;n<c.length;n++){//n=0~16
			if(bC1==c[n]){
				x=n;
			}
			if(bC2==c[n]){
				y=n;
			}
		}
		chesses[x][y]='@';
	}

	
	//�жϺڷ��Ƿ�ʤ��  
	//��������true���ڷ�ʤ��
	//����false���ڷ���δʤ��
	public static boolean isBlackWin(){
		//1)�жϺ����Ƿ���5��������@,�����,����true
		for(int i=1;i<17;i++){ //i=1~16 ��
			for(int j=1;j<13;j++){//j=1~12 ��
				if(chesses[i][j]=='@'&&
						chesses[i][j+1]=='@'
						&&chesses[i][j+2]=='@'
						&&chesses[i][j+3]=='@'
						&&chesses[i][j+4]=='@'){
					return true;
				}
			}
		}
		//2)�ж������Ƿ���5��������@,�����,����true
		for(int j=1;j<17;j++){//j=1~16 ��
			for(int i=1;i<13;i++){//i=1~12��
				if(chesses[i][j]=='@'
						&&chesses[i+1][j]=='@'
						&&chesses[i+2][j]=='@'
						&&chesses[i+3][j]=='@'
						&&chesses[i+4][j]=='@'){
					return true;
				}
			}
		}
		
		
		//3)�ж�\�Ƿ���5��������@
		for(int i=1;i<13;i++){//i=1~12 ��
			for(int j=1;j<13;j++){//j=1~12��
				if(chesses[i][j]=='@'
				  &&chesses[i+1][j+1]=='@'
				  &&chesses[i+2][j+2]=='@'
				  &&chesses[i+3][j+3]=='@'
				  &&chesses[i+4][j+4]=='@'){
					return true;
				}
			}
		}
		
		
		//4)�ж�/�Ƿ���5��������@
		for(int i=1;i<13;i++){//i=1~12��
			for(int j=5;j<17;j++){//j=5~16��
				if(chesses[i][j]=='@'
					&&chesses[i+1][j-1]=='@'
					&&chesses[i+2][j-2]=='@'
					&&chesses[i+3][j-3]=='@'
					&&chesses[i+4][j-4]=='@'){
					return true;
				}
			}
		}
		
		
		return false;
	}
	

	//�ж��ظ�����
	//����1,�ظ�������
	//����0,��������
	//���ݺڷ����������cB1��cB2�жϸ�λ�����Ƿ�������
	public static int isChess(char cB1,char cB2){
		char[] c={' ','0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		//�±�          0   1   2   3   4                                               15
		//bC1��bC2�Ǻڷ�������� ��
		int x=0; //���±�
		int y=0; //���±�
		for(int n=0;n<c.length;n++){//n=0~16
			if(cB1==c[n]){
				x=n;
			}
			if(cB2==c[n]){
				y=n;
			}
		}
		if(chesses[x][y]=='@'||chesses[x][y]=='0'){
			return 1;
		}else{
			return 0;
		}
	}
	
	

}






