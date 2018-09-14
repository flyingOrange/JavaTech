package designPattern.builder;

//步骤2： 电脑城老板委派任务给装机人员（Director）
public class Director {
	// 指挥装机人员组装电脑,主导装机的过程
	public void construct(Builder builder) {
		builder.buildCPU();
		builder.buildMainboard();
		builder.buildHD();
	}
}
