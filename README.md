# sfl4j-model-frame
Smart and fast deep learning for java （sfl4j）敏捷深度学习框架

基础模块——边框识别
该模块功能为识别图片边框（目前只能识别简单图片元素）

样例调用代码
图片入参对象java.awt.image.BufferedImage

List\<FramePointFor2D\> outPointList = FrameModel.findImageFrame(image, new LayerConfig(16, 20));

欢迎大家能够加入这个sfl4j项目，共同打造开源的、便捷好用的、java语言使用的，深度学习框架！~
