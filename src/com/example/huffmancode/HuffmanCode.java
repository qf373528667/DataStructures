package com.example.huffmancode;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//文件解压和压缩 有问题
//解压后多了 文件字节  位数多了几位
//另一个问题，bmp 单位存储的 图片，压缩后，再解压会报错    在编码表里有个    0=00  的huffman编码
public class HuffmanCode {

	public static void main(String[] args) {
		//测试压缩文件
//		String srcFile="d://src.bmp";
//		String dstFile="d://dst.zip";
//		zipFile(srcFile, dstFile);
//		System.out.println("压缩文件成功");
		
		//测试解压文件
		String zipFile="d://dst.zip";
		String dstFile="d://src2.bmp";
		unZipFile(zipFile, dstFile);
		System.out.println("解压成功");
		
		/*
		String content="i like like like java do you like a java";
		byte[] contentBytes=content.getBytes();
		System.out.println(contentBytes.length);//40
		byte[] huffmanZip = huffmanZip(contentBytes);
		System.out.println("压缩后的结果是："+Arrays.toString(huffmanZip)+"长度="+huffmanZip.length);//17个字节
		
		byte[] sourceBytes=decode(huffmanCodes,huffmanZip);
		System.out.println("原来的字符串="+new String(sourceBytes));
		*/
		
		//如何将数据 解压（解码）
		//分步过程
		/*
		List<Node> nodes=getNodes(contentBytes);
		System.out.println(nodes);
		
		//测试一把，创建的二叉树
		System.out.println("赫夫曼树");
		Node huffmanTreeRoot=createHuffmanTree(nodes);
		System.out.println("前序遍历");
		preOrder(huffmanTreeRoot);
		
		//测试一把是否生成了对应的赫夫曼编码
		Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
		System.out.println("生成的赫夫曼编码表"+huffmanCodes);
		
		byte[]huffmanCodeBytes=zip(contentBytes, huffmanCodes);
		System.out.println("huffmanCodeBytes="+Arrays.toString(huffmanCodeBytes));//17个字节
		
		//返送huffmanCodeBytes 数组
		 * 
		 */
	}
	
	//编写一个方法，完成对压缩文件的解压
	/**
	 * 
	 * @param zipFile   要解压的文件
	 * @param dstFile   解压到哪里
	 */
	public static void unZipFile(String zipFile,String dstFile) {
		//定义文件的输入流
		InputStream is=null;
		//定义一个对象输入流
		ObjectInputStream ois=null;
		//定义文件的输出流
		OutputStream os=null;
		try {
			//创建文件输入流
			is=new FileInputStream(zipFile);
			//创建一个和 is关联的对象输入流
			ois=new ObjectInputStream(is);
			//读取byte数组huffmanBytes
			byte[] huffmanBytes = (byte[])ois.readObject();
			System.out.println("huffmanBytes:"+Arrays.toString(huffmanBytes)+"长度："+huffmanBytes.length);
			//读取赫夫曼编码表
			Map<Byte,String> huffmanCodes=(Map<Byte,String>)ois.readObject();
			System.out.println("huffmanCodes:"+huffmanCodes+"长度："+huffmanCodes.size());
			//解码
			byte[] bytes=decode(huffmanCodes, huffmanBytes);
			//将bytes数组写入到目标文件
			os=new FileOutputStream(dstFile);
			//写出数据到dstFile文件中
			os.write(bytes);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(os!=null) {
					os.close();
				}
				if(ois!=null) {
					ois.close();
				}
				if(is!=null) {
					is.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//编写方法，将一个文件进行压缩
	/**
	 * 
	 * @param srcFile   你传入的希望压缩的文件的全路径
	 * @param dstFile   压缩后 将压缩文件放到哪个目录
	 */
	public static void zipFile(String srcFile,String dstFile) {
		//创建输出流
		FileInputStream is =null;
		FileOutputStream os=null;
		ObjectOutputStream oos=null;
		try {
			//创建输入流
			is = new FileInputStream(srcFile);
			//创建一个和源文件大小一样 的byte[]
			byte[] b=new byte[is.available()];
			//读取文件
			is.read(b);
			//直接对源文件压缩
			byte[] hufmanZip=huffmanZip(b);
			System.out.println("huffmanBytes:"+Arrays.toString(hufmanZip)+"长度："+hufmanZip.length);

			//创建文件的输出流，存放压缩文件
			os=new FileOutputStream(dstFile);
			//创建一个和文件输出流关联的 ObjectOutputStream
			oos = new ObjectOutputStream(os);
			//把 进行编码后的字节数组写入压缩文件
			oos.writeObject(hufmanZip);  //先把
			//这里我们以对象流的方式写入 赫夫曼编码，是为了以后我们恢复源文件时使用
			//这里一定要把赫夫曼编码写入压缩文件
			System.out.println("huffmanCodes:"+huffmanCodes+"长度："+huffmanCodes.size());
			oos.writeObject(huffmanCodes);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(is!=null)
				is.close();
				if(os!=null) {
					os.close();
				}
				if(oos!=null) {
					oos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	//完成数据的解压
	//1.将huffmanCodeBytes [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
	//  重新转成 赫夫曼编码对应的二进制字符串 "101010001..."
	//2. 将赫夫曼编码对应的二进制的字符串 "101010001..."  =》对照 赫夫曼编码 =》 转成"i like like like java do you like a java"
	
	//编写一个方法，完成对压缩数据的解码
	/**
	 * 
	 * @param huffmanCodes   赫夫曼编码表 map
	 * @param huffmanBytes   赫夫曼编码处理过的 字节数组（需要解码的字节数组）
	 * @return               就是原来的字符串对应的数组，（解码后的字节数组）
	 */
	private static byte[] decode(Map<Byte,String > huffmanCodes,byte[] huffmanBytes) {
		//1.先得到huffmanBytes 对应的二进制的字符串，形式101010001...
		StringBuilder stringBuilder = new StringBuilder();
		//将byte数组转成二进制的字符串
		for(int i=0;i<huffmanBytes.length;i++) {
			boolean flag=(i==huffmanBytes.length-1);
			stringBuilder.append(byteToBitString(!flag, huffmanBytes[i]));
		}
		System.out.println("解码赫夫曼字节数组对应的二进制字符串="+stringBuilder.toString());
		System.out.println("stringBuilder:"+stringBuilder.length());
		//把字符串按照指定的赫夫曼编码进行解码
		//把赫夫曼编码表进行调换，因为反向查询  97 -> 100  
		Map<String,Byte> map=new HashMap<String,Byte>();
		for(Map.Entry<Byte,String> entry:huffmanCodes.entrySet()) {
			map.put(entry.getValue(),entry.getKey());
		}
		//System.out.println("map="+map);
		//创建一个集合，存放byte
		List<Byte> list=new ArrayList<>();
		for(int i=0;i<stringBuilder.length();) {  //i 可以理解成就是索引，扫描stringBuilder
			int count=1;   //小的计数器
			boolean flag=true;
			Byte b=null;
			
			while(flag) {
				//取出一个'1'  或   '0'
				String key=stringBuilder.substring(i,i+count); //i不动，让count移动，指到匹配到一个字符
				b=map.get(key);
				System.out.println(key+"\t:"+b);
				if(b==null) { //说明没有匹配到
					count++;
				}else {  //匹配到
					flag=false;
				}
			}
			list.add(b);
			i+=count;   //i直接移动到count
		}
		//当for循环结束后，我们list中存放的所有字符  "i like like like java do you like a java"
		//把list中的数据放入到byte[] 并返回
		byte[] b=new byte[list.size()];
		for(int i=0;i<b.length;i++) {
			b[i]=list.get(i);
		}
		return b;
	}
	
	/**
	 *  将一个byte 转成一个二进制字符串
	 * @param b
	 * @param flag   标志是否需要补高位，如果是true，表示需要补高位，如果是false表示不补    最后一个字节 ，无需补高位
	 * @return       对应的二进制的字符串，（注意是按补码返回）
	 */
	private static String byteToBitString(boolean flag,byte b) {
		//使用变量保存 b
		int temp=b;//将b转成int
		//如果是正数，还需要存在补高位
		if(flag) {
			temp|=256;  //按位或256   1 0000 0000 |  0000 0001  =>  1 0000 0001
		}
		String str=Integer.toBinaryString(temp);  //返回的是temp对应的二进制补码
		
		if(flag) {
			return str.substring(str.length()-8);
		}else {
			return str;
		}
	}
	
	/**
	 *     使用一个方法，将前面的方法封装起来，便于我们的调用
	 * @param bytes  原始的字符串对应的字节数组
	 * @return       返回的是经过 赫夫曼编码 处理后的字节数组（压缩后的数组）
	 */
	
	private static byte[] huffmanZip(byte[] bytes) {
		List<Node> nodes=getNodes(bytes);
		//根据nodes创建赫夫曼树
		Node huffmanTreeRoot=createHuffmanTree(nodes);
		//生成对应的赫夫曼编码（根据 对应的赫夫曼树）
		Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
		// 根据生成的赫夫曼编码，压缩得到压缩后的赫夫曼编码字节数组
		byte[]huffmanCodeBytes=zip(bytes, huffmanCodes);
		return huffmanCodeBytes;
	}
	
	/**
	 *    编写一个方法，将字符串对应的byte[]数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码处理后的byte[]
	 * @param bytes         这是原始的字符串对应的byte[]
	 * @param huffmanCodes  生成的赫夫曼编码map
	 * @return              返回赫夫曼编码处理后的byte[]
	 */
	private static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodes) {
		//1.利用huffmanCodes 将 bytes转成 huffman编码后的字符串
		StringBuilder stringBuilder = new StringBuilder();
		//遍历bytes 数组
		for(byte b:bytes) {
			stringBuilder.append(huffmanCodes.get(b));
		}
		
		System.out.println("编码测试 stringBuilder="+stringBuilder.toString());
		System.out.println("stringBuilder:"+stringBuilder.length());
		//将"101010001..."转成byte[]
		//统计返回byte[] huffmanCodeBytes 长度
		//一句话搞定 int len=(stringBuilder.length()+7)/8;
		int len;
		if(stringBuilder.length()%8==0) {
			len=stringBuilder.length()/8;
		}else {
			len=stringBuilder.length()/8+1;
		}
		//创建存储压缩后的byte数组    huffmanCodeBytes
		byte[] huffmanCodeBytes=new byte[len];
		int index=0;//记录第几个byte
		for(int i=0;i<stringBuilder.length();i+=8) {  //因为每8位对应一个byte，所以步长+8
			String strByte;
			if(i+8>stringBuilder.length()) {  //最后如果不够8位
				strByte=stringBuilder.substring(i);
			}else {
				strByte=stringBuilder.substring(i,i+8);
			}
			//将strByte转成一个byte，放入到huffmanCodeBytes
			huffmanCodeBytes[index]=(byte)Integer.parseInt(strByte, 2);
			index++;
		}
		return huffmanCodeBytes;
	}
	//生成赫夫曼树对应的赫夫曼编码
	//思路
	//1. 将赫夫曼编码表存放在Map<Byte,String> 形式
	static Map<Byte,String> huffmanCodes=new HashMap<Byte,String>();
	//如  32->01 97->100 100->11000等等    [ 形式 ]
	//2.在生成赫夫曼编码表 时，需要去拼接路径，定义一个StringBuilder 存储某个叶子节点的路径
	static StringBuilder stringBuilder=new StringBuilder();
	
	//为了调用方便，我们重载getCodes
	private static Map<Byte,String> getCodes(Node root){
		if(root==null) {
			return null;
		}
		//如果root不等于空
		//处理root的左子树
		getCodes(root.left,"0",stringBuilder);
		//处理root的右子树
		getCodes(root.right,"1",stringBuilder);
		return huffmanCodes;
	}
	/**
	 *      功能：将传入的node节点的所有叶子节点的赫夫曼编码得到，并放入到huffmanCodes集合中
	 * @param node            传入的根节点
	 * @param code            路径：左子节点是0，右子节点是1
	 * @param stringBuilder   是用于拼接路径
	 */
	private static void getCodes(Node node,String code,StringBuilder stringBuilder) {
		StringBuilder stringBuilder2=new StringBuilder(stringBuilder);
		//将code 加入到stringBuilder2
		stringBuilder2.append(code);
		if(node!=null) { //如果node==null不处理
			//判断当前node是叶子节点 还是非叶子节点
			if(node.data==null) {  //说明是非叶子节点
				//递归处理
				//向左
				getCodes(node.left,"0", stringBuilder2);
				//向右递归
				getCodes(node.right,"1", stringBuilder2);
			}else {  //说明是一个叶子节点
				//就表示找到某个叶子节点
				huffmanCodes.put(node.data,stringBuilder2.toString());
			}
		}
	}
	//前序遍历的方法
	private static void preOrder(Node root) {
		if(root!=null) {
			root.preOrder();
		}else {
			System.out.println("赫夫曼树为空，不能遍历");
		}
	}
	
	/**
	 * 
	 * @param bytes  接收字节数组
	 * @return       返回节点集合 
	 */
	private static List<Node> getNodes(byte[] bytes){
		//1.创建一个ArrayList
		ArrayList<Node> nodes=new ArrayList<Node>();
		
		//遍历bytes，统计每一个byte出现的次数   存储每一个byte出现的次数->map
		Map<Byte,Integer> counts=new HashMap<Byte,Integer>();
		for(byte b:bytes) {
			Integer count=counts.get(b);
			if(count==null) { //Map还没有这个字符数据
				counts.put(b,1);//第一次
			}else {
				counts.put(b,count+1);
			}
		}
		//把每个键值对转成一个Node对象，并加入到nodes集合
		for(Map.Entry<Byte,Integer> entry:counts.entrySet()) {
			nodes.add(new Node(entry.getKey(),entry.getValue()));
		}
		return nodes;
	}

	//可以通过List 创建对应的赫夫曼树
	private static Node createHuffmanTree(List<Node> nodes) {
		while(nodes.size()>1) {
			//排序从小到大
			Collections.sort(nodes);
			
			//取出第一颗最小的二叉树
			Node leftNode=nodes.get(0);
			//取出第二颗第二小最小的二叉树
			Node rightNode=nodes.get(1);
			//创建一颗新的二叉树，它的根节点没有data，只有权值
			Node parent=new Node(null,leftNode.weight+rightNode.weight);
			parent.left=leftNode;
			parent.right=rightNode;
			//将已经处理的两个最小从nodes中移除
			nodes.remove(leftNode);
			nodes.remove(rightNode);
			//将新的二叉树，加入到nodes
			nodes.add(parent);
		}
		//nodes最后的节点，就是赫夫曼树的根节点 返回的节点
		return nodes.get(0);
	}
}

//创建Node，带数据和权值
class Node implements Comparable<Node>{
	Byte data;//存放数据本身 比如：'a' =>97   ' ' =>32
	int weight;//权值，表示字符出现的次数
	Node left;
	Node right;
	public Node(Byte data, int weight) {
		super();
		this.data = data;
		this.weight = weight;
	}
	@Override
	public int compareTo(Node o) {
		// 从小到大排序
		return this.weight-o.weight;
	}
	@Override
	public String toString() {
		return "Node [data=" + data + ", weight=" + weight + "]";
	}
	//前序遍历
	public void preOrder() {
		System.out.println(this);
		if(this.left!=null) {
			this.left.preOrder();
		}
		if(this.right!=null) {
			this.right.preOrder();
		}
	}
	
}