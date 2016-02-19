import scala.util.Random;
import scala.math._;
import scala.collection.mutable.ArrayBuffer;
class initial(layers:Array[Int]){
	var size = layers.length;
	var biases = ArrayBuffer[Array[Double]]();
	for (i <- 1 to size-1){
		var randn = new Random();
		var layern = new Array[Double](layers(i));
		for( a <- 0 to layers(i)-1){
			layern(a) = randn.nextGaussian();
		}
		biases += layern;
	}
	//tests 
	def get_biases(){
		var count = 0;
		print("hello");
		for (layer <- biases){
			println(f"layer $count%d");
			for(a <- 0 to layer.length-1){
				print(layer(a));
			}
			println(" ");
			count+=1;
		}
	}
	//var layer = new Array[Array[Int]];
	def get_length(){
		print(size);
	}
	def main(args:Array[String]){
		var test_net = new initial(Array(2,4,3));
		test_net.get_biases();
	}
	// def sigmoid(z: Double){
	// 	z = 1.0/(1.0)
	// }
}


