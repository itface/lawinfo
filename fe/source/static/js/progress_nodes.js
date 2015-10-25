var data = [
  {
    text: "合约洽谈",
    state: {
        checked: true,
        expanded: false,
        selectable: false
    },
    nodes: [
      {
        text: "银行委托洽谈"
      },
      {
        text: "合同已签订"
      },
      {
        text: "初期律师费已付"
      },
      {
        text: "诉前财产保全"
      }
    ]
  },
  {
    text: "一审已立案",
    state: {
        expanded: false,
    },
    nodes: [
      {
        text: "立案"
      },
      {
        text: "财产保全"
      },
      {
        text: "文书送达"
      },
      {
        text: "确定开庭日"
      }
    ]
  },
  {
    text: "一审已开庭",
    state: {
        expanded: false
    },
    nodes: [
      {
        text: "已开庭"
      },
      {
        text: "调解进行中"
      },
      {
        text: "代理文书提交"
      },
      {
        text: "判决书跟进"
      }
    ]
  },
  {
    text: "一审已判决",
    state: {
       expanded: false
    },
    nodes: [
      {
        text: "法院已出具判决书",
      },
      {
        text: "判决书送达"
      }
    ]
  },
  {
    text: "上诉期",
    state: {
        expanded: false
    },
    nodes: [
      {
        text: "已上诉"
      }
    ]
  },
  {
    text: "二审已立案",
    state: {
        expanded: false
    },
    nodes: [
        {
            text: "立案"
        },
        {
            text: "文书送达"
        },
        {
            text: "确定开庭日"
        }
    ]
  },
  {
    text: "二审已开庭",
    state: {
        expanded: false
    },
    nodes: [
        {
            text: "已开庭"
        },
        {
            text: "调解进行中"
        },
        {
            text: "代理文书提交"
        },
        {
            text: "判决书跟进"
        }
    ]
  },
  {
    text: "二审已判决",
    state: {
        expanded: false
    },
    nodes: [
        {
            text: "法院已出具判决书"
        },
        {
            text: "判决书送达"
        }
    ]
  },
  {
    text: "执行阶段",
    state: {
        expanded: false
    },
    nodes: [
        {
            text: "已申请执行立案"
        },
        {
            text: "已确定执行法官"
        },
        {
            text: "执行文书送达"
        },
        {
            text: "财产拍卖"
        },
        {
            text: "结案"
        }
    ]
  },
  {
    text: "律师费结案",
    state: {
        expanded: false
    },
    nodes: [
        {
            text: "提交律师费结算"
        },
        {
            text: "开出发票"
        },
        {
            text: "律师费到账"
        }
    ]
  }
];

module.exports = data;