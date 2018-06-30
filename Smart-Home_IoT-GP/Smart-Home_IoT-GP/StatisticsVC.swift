
import UIKit
import QuartzCore

class StatisticsVC: ParentViewController, LineChartDelegate {

    //MARK:- varibales
    var label = UILabel()
    var lineChart: LineChart!
    
    var device: Device!
    
    //MARK:- view methods
    override func viewDidLoad() {
        super.viewDidLoad()
//        showLoading()
        
//        LogServices.getDeviceLogs(homeId: device.home.id, deviceId: device.id) { (status, logs) in
//            if status == "success"
//            {
//                if self.device.getAllLogs().count > 0
//                {
//                    self.device.removeAllLogs()
//                }
//                self.device.addLogs(logs: logs)
//                self.chartSetup()
//            }
//            else
//            {
//                let alert = UIAlertController(title: "Error", message: status, preferredStyle: .alert)
//                let action = UIAlertAction(title: "Dismiss", style: .cancel, handler: nil)
//                alert.addAction(action)
//                self.present(alert, animated: true, completion: nil)
//            }
//         self.hideLoading()
//        }
        
        chartSetup()
        

    
    }
    
    
    override func viewWillDisappear(_ animated: Bool)
    {
        super.viewWillDisappear(animated)
        
        if (self.isMovingFromParentViewController)
        {
//            UIDevice.current.setValue(Int(UIInterfaceOrientation.portrait.rawValue), forKey: "orientation")
        }
    }
    
    //MARK:- methods
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
        
        var i = 0
        while i < deviceLogs.count
        {
            if !(i >= deviceLogs.count - 1)
            {
                
                let serverDateFormatter: DateFormatter = {
                    let result = DateFormatter()
                    result.dateFormat = "yyyy-MM-dd HH:mm:ss"
                    result.timeZone = NSTimeZone(forSecondsFromGMT: 0) as TimeZone!
                    return result
                }()
                
                let earlierDate = serverDateFormatter.date(from: deviceLogs[i].timeStamp)!
                
                let laterDate = serverDateFormatter.date(from: deviceLogs[i+1].timeStamp)!
                let interval = laterDate.timeIntervalSince(earlierDate)
                upTimeForDevice.append(CGFloat(interval))
                
                let formatter = DateFormatter()
                formatter.dateFormat = "mm:ss"
                logsTimeStamps.append(formatter.string(from: laterDate))
                
            }
            i += 2
        }
        
        
        
        
        
        lineChart = LineChart()
        lineChart.animation.enabled = true
        lineChart.area = true
        lineChart.x.labels.visible = true
        lineChart.x.grid.count = CGFloat(upTimeForDevice.count)
        lineChart.y.grid.count = CGFloat(upTimeForDevice.count)
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
    
    //MARK:- Line chart delegate method.
    func didSelectDataPoint(_ x: CGFloat, yValues: Array<CGFloat>) {
        label.text = "x: \(x)     y: \(yValues)"
    }
    
     //MARK:- Redraw chart on device rotation.
    override func didRotate(from fromInterfaceOrientation: UIInterfaceOrientation) {
        if let chart = lineChart {
            chart.setNeedsDisplay()
        }
    }

}
