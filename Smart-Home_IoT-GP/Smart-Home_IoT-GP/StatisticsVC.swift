
import UIKit
import QuartzCore

class StatisticsVC: UIViewController, LineChartDelegate {

    
    
    var label = UILabel()
    var lineChart: LineChart!
    
    var device: Device!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        var views: [String: AnyObject] = [:]
        
        label.text = "..."
        label.translatesAutoresizingMaskIntoConstraints = false
        label.textAlignment = NSTextAlignment.center
        self.view.addSubview(label)
        views["label"] = label
        view.addConstraints(NSLayoutConstraint.constraints(withVisualFormat: "H:|-[label]-|", options: [], metrics: nil, views: views))
        view.addConstraints(NSLayoutConstraint.constraints(withVisualFormat: "V:|-80-[label]", options: [], metrics: nil, views: views))
        
        // simple arrays
        let data: [CGFloat] = [3, 4, -2, 11, 13, 15]
        let data2: [CGFloat] = [1, 3, 5, 13, 17, 20]
        
        // simple line with custom x axis labels
        let xLabels: [String] = ["Jan", "Feb", "Mar", "Apr", "May", "Jun"]
        
        lineChart = LineChart()
        lineChart.animation.enabled = true
        lineChart.area = true
        lineChart.x.labels.visible = true
        lineChart.x.grid.count = 5
        lineChart.y.grid.count = 5
        lineChart.x.labels.values = xLabels
        lineChart.y.labels.visible = true
        lineChart.addLine(data)
        lineChart.addLine(data2)
        
        lineChart.translatesAutoresizingMaskIntoConstraints = false
        lineChart.delegate = self
        self.view.addSubview(lineChart)
        views["chart"] = lineChart
        lineChart.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 20).isActive = true
        lineChart.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -20).isActive = true
        lineChart.topAnchor.constraint(equalTo: view.topAnchor, constant: 50).isActive = true
        lineChart.bottomAnchor.constraint(equalTo: view.bottomAnchor, constant: -50).isActive = true
    

        print(device.name)
    }
    
    
    override func viewWillDisappear(_ animated: Bool)
    {
        super.viewWillDisappear(animated)
        
        if (self.isMovingFromParentViewController)
        {
            UIDevice.current.setValue(Int(UIInterfaceOrientation.portrait.rawValue), forKey: "orientation")
        }
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    func canRotate(){}
    
    
    
    /**
     * Line chart delegate method.
     */
    func didSelectDataPoint(_ x: CGFloat, yValues: Array<CGFloat>) {
        label.text = "x: \(x)     y: \(yValues)"
    }
    
    
    
    /**
     * Redraw chart on device rotation.
     */
    override func didRotate(from fromInterfaceOrientation: UIInterfaceOrientation) {
        if let chart = lineChart {
            chart.setNeedsDisplay()
        }
    }

}
