package day1026;

import java.util.Scanner;

//五子棋游戏
//1)棋盘的绘制
//2)黑方和白方落子 
//3)在落子对应的位置将棋盘修改为@  0
//4)判断胜利
//5)判断重复落子的问题
public class FiveChessGame {
	//定义二维数组,表示五子棋盘
	static char[][] chesses=new char[17][17];
	
	public static void main(String[] args) {
		//填充五子棋盘的内容
		fillChess();
		
		//输出五子棋盘的内容
		printChess();
		
		Scanner sc=new Scanner(System.in);
		boolean flag=true;//开关
		//flag为true黑方执行 
		//flag为false白方执行
		for(;;){  //死循环
			if(flag){  //控制黑方落子
				//黑方落子
				System.out.println("请黑方落子:");
				String strBlack=sc.next(); //34
				System.out.println("黑方输入的内容:"+strBlack);
				//根据黑方落子的位置将*修改为@
				//chesses[4][5]='@'; //临时的
				char bC1=strBlack.charAt(0);
				char bC2=strBlack.charAt(1);
				//判断重复落子
				int m=isChess(bC1,bC2);
				if(m==1){ //重复落子了
					flag=true;
					System.out.println("重复落子了");
				}else{
					updateBlack(bC1,bC2);
					//重新输出棋盘
					printChess();
					//判断黑方是否胜利
					boolean f1=isBlackWin();
					if(f1==true){
						System.out.println("黑方胜利,游戏结束");
						System.exit(0);
					}
					flag=false;
				}
				
			}
			
			if(!flag){ //控制白方落子
				//白方落子
				System.out.println("请白方落子:");
				String strWhite=sc.next(); //a1
				System.out.println("白方输入的内容:");
				//根据白方落子的位置将*修改为0
				chesses[11][2]='0';
				//重新输出棋盘
				printChess();
				flag=true;
			}
			
			
			
			
			
			
		}
		
	
	
	}
	
	//定义一个方法 -- 输出五子棋盘的内容
	public static void printChess(){
		//输出五子棋盘的内容
		for(int i=0;i<chesses.length;i++){
			for(int j=0;j<chesses[i].length;j++){
				System.out.print(chesses[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	//定义一个方法-- 填充五子棋盘的内容
	public static void fillChess(){
		//  0 1 2 3 4 5 6 7 8 9 a b c d e f
		//0 * * * * * * * * * * * * * * * *
		//1 * * * * * * * * * * * * * * * *
		//2 * * * * * * * * * * * * * * * *
		//填充五子棋盘的内容
		char[] c={' ','0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		//单独填充棋盘的第0行
		for(int n=0;n<c.length;n++){//n=0~16
			chesses[0][n]=c[n];
		}
		//填充棋盘的第1行~第16行
		for(int i=1;i<chesses.length;i++){//i=1~16
			//填充第0列
			chesses[i][0]=c[i];
			//填充的是第1列~第16列
			for(int j=1;j<chesses[i].length;j++){
				chesses[i][j]='*';
			}
		}
	}
	
	//定义一个方法 --根据黑方输入的行列，找到
	//实际的行下标和列下标,将*修改为@
	//bC1 a bC2 3
	public static void updateBlack(char bC1,char bC2){
		char[] c={' ','0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		//下标          0   1   2   3   4                                               15
		//bC1和bC2是黑方输入的行 列
		int x=0; //行下标
		int y=0; //列下标
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

	
	//判断黑方是否胜利  
	//方法返回true，黑方胜利
	//返回false，黑方还未胜利
	public static boolean isBlackWin(){
		//1)判断横向是否有5个连续的@,如果有,返回true
		for(int i=1;i<17;i++){ //i=1~16 行
			for(int j=1;j<13;j++){//j=1~12 列
				if(chesses[i][j]=='@'&&
						chesses[i][j+1]=='@'
						&&chesses[i][j+2]=='@'
						&&chesses[i][j+3]=='@'
						&&chesses[i][j+4]=='@'){
					return true;
				}
			}
		}
		//2)判断纵向是否有5个连续的@,如果有,返回true
		for(int j=1;j<17;j++){//j=1~16 列
			for(int i=1;i<13;i++){//i=1~12行
				if(chesses[i][j]=='@'
						&&chesses[i+1][j]=='@'
						&&chesses[i+2][j]=='@'
						&&chesses[i+3][j]=='@'
						&&chesses[i+4][j]=='@'){
					return true;
				}
			}
		}
		
		
		//3)判断\是否有5个连续的@
		for(int i=1;i<13;i++){//i=1~12 行
			for(int j=1;j<13;j++){//j=1~12列
				if(chesses[i][j]=='@'
				  &&chesses[i+1][j+1]=='@'
				  &&chesses[i+2][j+2]=='@'
				  &&chesses[i+3][j+3]=='@'
				  &&chesses[i+4][j+4]=='@'){
					return true;
				}
			}
		}
		
		
		//4)判断/是否有5个连续的@
		for(int i=1;i<13;i++){//i=1~12行
			for(int j=5;j<17;j++){//j=5~16列
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
	

	//判断重复落子
	//返回1,重复落子了
	//返回0,正常落子
	//根据黑方输入的行列cB1和cB2判断该位置上是否有棋子
	public static int isChess(char cB1,char cB2){
		char[] c={' ','0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		//下标          0   1   2   3   4                                               15
		//bC1和bC2是黑方输入的行 列
		int x=0; //行下标
		int y=0; //列下标
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






