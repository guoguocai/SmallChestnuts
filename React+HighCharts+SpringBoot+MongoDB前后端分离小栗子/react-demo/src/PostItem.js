import React, { Component } from 'react';

class PostItem extends Component {
    render(){
        const {userId, name, uclass, email, age, dataStatus} = this.props;
        return (
            <div>
                <div>
                    Chrome:<span>{ userId }</span>
                </div>
                <div>
                    Internet Explorer：<span>{ name }</span>
                </div>
                <div>
                    Firefox：<span>{ uclass }</span>
                </div>
                <div>
                    Edge：<span>{ email }</span>
                </div>
                <div>
                    Safari：<span>{ age }</span>
                </div>
                <div>
                    Other：<span>{ dataStatus }</span>
                </div>
            </div>
        );
    }
}

export default PostItem;
