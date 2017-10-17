GraphViz.java 中第一个绝对地址是缓存路径，用于缓存生成的图像
第二个绝对地址是Graphviz-2.38中的的dot.exe文件地址

程序入口为 AppMainWindow.java 

后可以跳转到 InputPathPanel.java 或者 FileChoosePanel.java（内部的监听器部分参照java核心技术卷1）

得到文件后  会有一点卡顿的现象  

后跳转到functionPanel包中的 FunctionChoosePanel.java
根据选择的功能不同 跳转到的面板（java文件）也不同

由于自己写的图像查看器 效率很低  尤其是在生成最短路中 只给出起始点的情况下 

生成最短路 不可达的情况 没有注意到   底层有判断   gui没有用  所以会报错
