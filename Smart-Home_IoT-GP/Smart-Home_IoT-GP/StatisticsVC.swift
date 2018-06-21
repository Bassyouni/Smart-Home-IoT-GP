
import UIKit
import QuartzCore

class StatisticsVC: ParentViewController, LineChartDelegate {

    
    
    var label = UILabel()
    var lineChart: LineChart!
    
    var device: Device!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        showLoading()
        
        LogServices.getDeviceLogs(homeId: device.home.id, deviceId: device.id) { (status, logs) in
            if status == "success"
            {
                if self.device.getAllLogs().count > 0
                {
                    self.device.removeAllLogs()
                }
                self.device.addLogs(logs: logs)
                self.chartSetup()
            }
            else
            {
                let alert = UIAlertController(title: "Error", message: status, preferredStyle: .alert)
                let action = UIAlertAction(title: "Dismiss", style: .cancel, handler: nil)
                alert.addAction(action)
                self.present(alert, animated: true, completion: nil)
            }
         self.hideLoading()
        }
        
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
    
    }
    
    
    override func viewWillDisappear(_ animated: Bool)
    {
        super.viewWillDisappear(animated)
        
        if (self.isMovingFromParentViewController)
        {
//            UIDevice.current.setValue(Int(UIInterfaceOrientation.portrait.rawValue), forKey: "orientation")
        }
    }
    
    func chartSetup()
    {
        var views: [String: AnyObject] = [:]
        
        label.text = "..."
        label.translatesAutoresizingMaskIntoConstraints = false
        label.textAlignment = NSTextAlignment.center
        self.view.addSubview(label)
        views["label"] = label
        view.addConstraints(NSLayoutConstraint.constraints(withVisualFormat: "H:|-[label]-|", options: [], metrics: nil, views: views))
        view.addConstraints(NSLayoutConstraint.constraints(withVisualFormat: "V:|-80-[label]", options: [], metrics: nil, views: views))

        var deviceLogs = device.getAllLogs()
        var logsTimeStamps = [String]()
        var upTimeForDevice = [CGFloat]()
        for log in deviceLogs
        {
            logsTimeStamps.append(log.timeStamp)
        }
        
        for i in 0...deviceLogs.count
        {
            guard let time1 = NumberFormatter().number(from: deviceLogs[i+1].timeStamp) else { return }
            guard let time2 = NumberFormatter().number(from: deviceLogs[i].timeStamp) else { return }
            
            let diffrecne:CGFloat = CGFloat(time1) - CGFloat(time2)
            upTimeForDevice.append(diffrecne)
        }
        
        
        
        lineChart = LineChart()
        lineChart.animation.enabled = true
        lineChart.area = true
        lineChart.x.labels.visible = true
        lineChart.x.grid.count = CGFloat(deviceLogs.count)
        lineChart.y.grid.count = CGFloat(deviceLogs.count)
        lineChart.x.labels.values = logsTimeStamps
        lineChart.y.labels.visible = true
        lineChart.addLine(upTimeForDevice)
        
        lineChart.translatesAutoresizingMaskIntoConstraints = false
        lineChart.delegate = self
        self.view.addSubview(lineChart)
        views["chart"] = lineChart
        lineChart.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 20).isActive = true
        lineChart.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -20).isActive = true
        lineChart.topAnchor.constraint(equalTo: view.topAnchor, constant: 50).isActive = true
        lineChart.bottomAnchor.constraint(equalTo: view.bottomAnchor, constant: -50).isActive = true
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
