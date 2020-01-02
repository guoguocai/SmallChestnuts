import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import MyPieChart from './MyPieChart';
import MyLineChart from './MyLineChart';
import MyReactTest from './MyReactTest';
import * as serviceWorker from './serviceWorker';

ReactDOM.render(<MyPieChart />, document.getElementById('container'));
ReactDOM.render(<MyReactTest />, document.getElementById('MyReactTest'));
ReactDOM.render(<MyLineChart />, document.getElementById('line_container'));
//ReactDOM.render(<App />, document.getElementById('root'));


// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
