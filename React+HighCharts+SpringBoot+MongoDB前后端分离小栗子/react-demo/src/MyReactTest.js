import React, { Component } from 'react';
import Highcharts from 'highcharts';
import HighchartsReact from 'highcharts-react-official';
import PostItem from './PostItem';
import axios from 'axios';
import $ from 'jquery';

class MyReactTest extends Component {
    constructor(props) {
        super(props);
        this.state = {
            news:[
                {
                    userId: 11,
                    name: 61,
                    uclass: 4,
                    email: 7,
                    age: 10,
                    dataStatus: 7
                }
            ]
        };
    }
    handle_click = () => {
        let t = this;
        axios.get('/mongodbController/query').then(({data}) => {
            console.log(data)
            t.setState({
                // 这里需要根据返回的 json 串的结构来取值
                news:data.data
            });
        });
    };
    render(){
        const options = {
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            title: {
                text: 'Browser market shares in January, 2018'
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: true
                }
            },
            series: [{
                name: 'Brands',
                colorByPoint: true,
                data: [{
                    name: 'Chrome',
                    y: this.state.news[0].userId,
                    sliced: true,
                    selected: true
                }, {
                    name: 'Internet Explorer',
                    y: this.state.news[0].name
                }, {
                    name: 'Firefox',
                    y: this.state.news[0].uclass
                }, {
                    name: 'Edge',
                    y: this.state.news[0].email
                }, {
                    name: 'Safari',
                    y: this.state.news[0].age
                }, {
                    name: 'Other',
                    y: this.state.news[0].dataStatus
                }]
            }]
        };
        return (
            <div>
                <button onClick={this.handle_click}>Update Data</button>
                <br/>
                Post lists:
                {
                    this.state.news.map(
                        (item,i) =>
                            <div key={i}>
                                <PostItem
                                    userId = {item.userId}
                                    name = {item.name}
                                    uclass = {item.uclass}
                                    age = {item.age}
                                    email = {item.email}
                                    dataStatus = {item.dataStatus}
                                />
                            </div>
                    )
                }
                <div>
                    <HighchartsReact
                        highcharts={Highcharts}
                        constructorType={'chart'}
                        options={options}
                    />
                </div>
            </div>
        );
    }

}

export default MyReactTest;
