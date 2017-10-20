package dot;

import java.io.File;

public class DotCompiler {
	//private static final String DEFAULT_CACHE_PATH = "D:\\Lab1\\bin\\Dot\\cache";
	private static String cachePath = "/home/siyuan/cache";

	public static String saveFile(String textFilePath, String dotSource) {
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());
		gv.add(dotSource);
		gv.addln(gv.end_graph());
		String type = "png";
		String imageFilePath = null;
		int suffixDotIndex = textFilePath.indexOf((int)'.');
		if (suffixDotIndex == -1)
			imageFilePath = textFilePath;
		else {
			imageFilePath = textFilePath.substring(0, suffixDotIndex + 1);}
		File out = new File(imageFilePath + type);
		gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);
		return imageFilePath + type;
	}
	public static String saveTempFile(String fileName, String dotSource) {
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());
		gv.add(dotSource);
		gv.addln(gv.end_graph());
		String type = "png";
		String imageFilePath = cachePath + "\\" + fileName;
		File out = new File(imageFilePath + "." + type);
		gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);
		return imageFilePath + "." + type;
	}
	public static void clearCache() {
		File cacheDir = new File(cachePath);
		String[] children = cacheDir.list();
		if(children != null) {
			for (String fileName : children) {
				File cacheFile = new File(cachePath + "//" + fileName);
				if(cacheFile != null) {
					cacheFile.delete();
				}
			}
		}
	}
}